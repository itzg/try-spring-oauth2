package me.itzg.tryspringoauth2.config;

import me.itzg.tryspringoauth2.impersonate.ImpersonateConfigurer;
import me.itzg.tryspringoauth2.impersonate.ImpersonateProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableConfigurationProperties(ImpersonateProperties.class)
public class SecurityConfiguration {

    private final Environment environment;
    private final ImpersonateProperties impersonateProperties;

    public SecurityConfiguration(Environment environment, ImpersonateProperties impersonateProperties) {
        this.environment = environment;
        this.impersonateProperties = impersonateProperties;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/app/**", "/app.html", "/api/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults())
            // https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html#oauth2-client-log-users-in
            .oauth2Login(c -> c.defaultSuccessUrl("/app/"));

        if (
            // If this code were to be used in production, it's good practice to
            // further restrict impersonation to dev profile activation
            environment.matchesProfiles("dev")
                && impersonateProperties.enabled()) {
            http.with(new ImpersonateConfigurer<>(), c -> c.withName(impersonateProperties.name()));
        }

        return http.build();
    }
}
