package co.istad.springbankingapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Bean
    SecurityFilterChain configFilterChain(HttpSecurity http) throws Exception {

        // Protect routes
//        http.authorizeHttpRequests(endpoint -> endpoint
//                .requestMatchers("/api/v1/account-types").permitAll()
//                .requestMatchers(HttpMethod.POST,"/api/v1/users").hasRole("ADMIN")
//                .anyRequest().authenticated()
//        );

//        http.authorizeHttpRequests(
//                endpoint -> endpoint
//                        .anyRequest().authenticated()
//        );

        // Security Mechanism
//        http.httpBasic(Customizer.withDefaults());
        http.oauth2ResourceServer(
                oauth2 -> oauth2.jwt(Customizer.withDefaults())
        );
        // Disable CSRF Token
        http.csrf(AbstractHttpConfigurer::disable);

        // Make API stateless
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
