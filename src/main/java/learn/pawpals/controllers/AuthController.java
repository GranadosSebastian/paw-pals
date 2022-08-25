package learn.pawpals.controllers;

import learn.pawpals.domain.Result;
import learn.pawpals.models.AppUser;
import learn.pawpals.models.Credentials;
import learn.pawpals.security.AppUserService;
import learn.pawpals.security.JwtConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
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

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody Credentials credentials) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);

        if (authentication.isAuthenticated()) {
            String jwtToken = jwtConverter.getTokenFromUser((User) authentication.getPrincipal());
            HashMap<String, String> map = new HashMap<>();
            map.put("jwt_token", jwtToken);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

//    @PostMapping("/register")
//    public ResponseEntity<Object> register(@RequestBody Credentials credentials) {
//        Result<AppUser> result = service.create(credentials);
//        if (result.isSuccess()) {
//            HashMap<String, Integer> map = new HashMap<>();
//            map.put("app_user_id", result.getPayload().getAppUserId());
//            return new ResponseEntity<>(map, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refresh(UsernamePasswordAuthenticationToken principal) {
        User user = new User(principal.getName(), principal.getName(), principal.getAuthorities());
        String jwtToken = jwtConverter.getTokenFromUser(user);
        HashMap<String, String> map = new HashMap<>();
        map.put("jwt_token", jwtToken);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
