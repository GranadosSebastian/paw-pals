package learn.pawpals.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtConverter jwtConverter;

    public SecurityConfig(JwtConverter jwtConverter) {
        this.jwtConverter = jwtConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable();

        http.cors();

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/authenticate", "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/refresh-token").authenticated()
                .antMatchers(HttpMethod.POST,"/api/animal/appuser").permitAll()
                .antMatchers(HttpMethod.GET, "/api/animal", "/api/animal/*", "/api/animal/schedule", "/api/animal/schedule/*", "/api/animal/schedule/animalId/*", "/api/animal/schedule/appUserId/*", "/api/animal/appuser").permitAll()
                .antMatchers(HttpMethod.POST, "/api/animal", "/api/animal/schedule", "/api/animal/appuser").hasAnyRole("staff", "volunteer", "foster_parent", "adopter")
                .antMatchers(HttpMethod.PUT, "/api/animal/*", "/api/animal/schedule/*", "/api/animal/appuser/*").hasAnyRole("staff", "volunteer", "foster_parent", "adopter")
                .antMatchers(HttpMethod.DELETE, "/api/animal/*", "api/animal/schedule/*", "/api/animal/appuser/*").hasAnyRole("staff", "volunteer", "foster_parent", "adopter")
              //  .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), jwtConverter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Autowired
//    private PasswordEncoder encoder;

}