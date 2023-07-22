package com.example.secureweb.config;

import com.example.secureweb.security.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/secure/**").authenticated()
                        .anyRequest().permitAll()
        );
        http.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.loginPage("/login");
            httpSecurityFormLoginConfigurer.failureUrl("/login?error");
            httpSecurityFormLoginConfigurer.defaultSuccessUrl("/secure");
        });
        return http.build();
    }
}
