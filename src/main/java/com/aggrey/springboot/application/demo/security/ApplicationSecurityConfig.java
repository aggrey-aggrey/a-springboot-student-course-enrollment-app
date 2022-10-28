package com.aggrey.springboot.application.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.aggrey.springboot.application.demo.security.ApplicationUserRoles.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index","/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userJohnyCool = User.builder()
                .username("Johny")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name()) //ROLE_STUDENT
                .build();

        UserDetails userLisaLisa = User.builder()
                .username("Lisa")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name()) //ROLE_STUDENT
                .build();

        UserDetails userRayRaymond = User.builder()
                .username("Ray")
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name())
                .build();//ROLE_ADMIN  //linda

        UserDetails userAshleyPerkins = User.builder()
                .username("Ashley")
                .password(passwordEncoder.encode("password"))
                .roles(ADMINTRAINEE.name())
                .build();//ROLE_ADMIN_TRAINEE   //tom

        return new InMemoryUserDetailsManager(userJohnyCool, userRayRaymond, userAshleyPerkins, userLisaLisa);

    }
}

