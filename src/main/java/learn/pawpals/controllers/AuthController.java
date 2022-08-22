
package learn.pawpals.controllers;

import learn.pawpals.security.AppUserService;
import learn.pawpals.security.JwtConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;

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

    //@PostMapping("/authenticate")
    public void authenticate() {
     }

     //@PostMapping("/register")
    public void register() {
    }

    //@PostMapping("/refresh-token")
    public void refresh() {
    }

}
