package learn.pawpals.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                .antMatchers(HttpMethod.POST, "/authenticate", "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/refresh-token").authenticated()
                .antMatchers(HttpMethod.GET, "/api/animal", "/api/animal/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/animal").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/animal/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/animal/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/appuser", "/api/appuser/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/appuser").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/appuser/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/appuser/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/schedule", "/api/schedule/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/schedule").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/schedule/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/schedule/*").hasAnyRole("ADMIN")
                .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), jwtConverter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}