package learn.pawpals.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import learn.pawpals.models.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtConverter {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final String ISSUER = "animal-api";
    private final int EXPIRATION_MINUTES = 480;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;

    public String getTokenFromUser(AppUser appUser) {

        String authorities = appUser.getAuthorities().stream()
                .map(i -> i.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(appUser.getUsername())
                .claim("authorities", authorities)
                .claim("appUserId", appUser.getAppUserId())
                .claim("phone", appUser.getPhone())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(key)
                .compact();
    }

    public AppUser getUserFromToken(String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.substring(7));

            String username = jws.getBody().getSubject();

            String authStr = (String) jws.getBody().get("authorities");

            List<String> authorities = List.of(authStr.split(",");

            int appUserId = (int) jws.getBody().get("appUserId");
            String phone = (String) jws.getBody().get("phone");

            return new AppUser(appUserId, phone, username, username, false, authorities);

        } catch (JwtException e) {
            e.printStackTrace();
        }

        return null;
    }

}
