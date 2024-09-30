package com.netology.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("/persons/by").permitAll()
                .and()
                .authorizeRequests().antMatchers("/persons/by-age").hasAuthority("/persons/by-age")
                .and()
                .authorizeRequests().anyRequest().authenticated();

    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Sat")
                .password(encoder().encode("password"))
                .authorities("/persons/by-age")
                .and()
                .withUser("Ivan")
                .password(encoder().encode("password1"))
                .authorities("/persons/by-city_of_living");
    }


}
