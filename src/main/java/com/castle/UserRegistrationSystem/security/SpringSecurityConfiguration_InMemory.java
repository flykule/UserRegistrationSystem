package com.castle.UserRegistrationSystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SpringSecurityConfiguration_InMemory extends WebSecurityConfigurerAdapter {

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        builder.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("123456"))
                .roles("USER");

        builder.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("USER","ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/user/")
                .hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/user/")
                .hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/user/**")
                .hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/user/**")
                .hasRole("ADMIN")
                .and()
                .csrf()
                .disable();
    }
}
