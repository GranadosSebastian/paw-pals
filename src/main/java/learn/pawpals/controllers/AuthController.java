package learn.pawpals.controllers;

import learn.pawpals.models.AppUser;
import learn.pawpals.domain.AppUserService;
import learn.pawpals.security.JwtConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtConverter jwtConverter;
    private final AppUserService service;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtConverter jwtConverter,
                          AppUserService service) {
        this.authenticationManager = authenticationManager;
        this.jwtConverter = jwtConverter;
        this.service = service;
    }

    @PostMapping("/api/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"));

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                HashMap<String, String> map = new HashMap<>();

                AppUser appUser = (AppUser)authentication.getPrincipal();
                String token = jwtConverter.getTokenFromUser(appUser);
                map.put("jwt_token", token);

                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    /*
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Credentials credentials) {
        Result<AppUser> result = service.add(credentials);
        if (result.isSuccess()) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("appUserId", result.getPayload().getAppUserId());
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refresh(UsernamePasswordAuthenticationToken principal) {
        AppUser appUser = new AppUser(principal.getName(), principal.getName(), principal.getAuthorities());
        String jwtToken = jwtConverter.getTokenFromUser(appUser);
        HashMap<String, String> map = new HashMap<>();
        map.put("jwt_token", jwtToken);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    */

}
