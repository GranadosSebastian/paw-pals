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
                .claim("username", appUser.getUsername())
                .claim("passwordHash", appUser.getPassword())
                .claim("disabled", appUser.isEnabled())
                .claim("firstName", appUser.getFirstName())
                .claim("lastName", appUser.getLastName())
                .claim("address", appUser.getAddress())
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

            int appUserId = (int) jws.getBody().get("appUserId");

            String username = jws.getBody().getSubject();

            String firstName = (String) jws.getBody().get("firstName");

            String lastName = (String) jws.getBody().get("lastName");

            String address = (String) jws.getBody().get("address");

            String phone = (String) jws.getBody().get("phone");


            String authStr = (String) jws.getBody().get("authorities");

            List<String> authorities = List.of(authStr.split(","));


            return new AppUser(appUserId, username, username, false, firstName, lastName, address, phone, authorities);

        } catch (JwtException e) {
            System.out.println(e);
        }

        return null;
    }

}
