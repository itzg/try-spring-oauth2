package me.itzg.tryspringoauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/app/**", "/app.html", "/api/**").authenticated()
                .anyRequest().permitAll()
            )
            // https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html#oauth2-client-log-users-in
            .oauth2Login(withDefaults());

        return http.build();
    }
}
