package com.example.storageofthings.fw;

import com.example.storageofthings.app.user.UserLoadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        HttpSecurity httpSecurity,
        UserLoadingService userLoadingService
    ) throws Exception {
        var builder = httpSecurity
            .getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userLoadingService);
        return builder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(AbstractHttpConfigurer::disable);
        httpSecurity
            .authorizeHttpRequests(requestMatcherRegistry ->
                requestMatcherRegistry
                    .requestMatchers("/registration/**").not().fullyAuthenticated()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
            );
        httpSecurity
            .authorizeHttpRequests(requestMatcherRegistry ->
                requestMatcherRegistry.anyRequest().authenticated());
        httpSecurity
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/thing")
            );
        httpSecurity
            .logout(
                logout ->
                    logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
            );
        return httpSecurity.build();
    }

}
