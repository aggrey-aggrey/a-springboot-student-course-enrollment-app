package com.aggrey.springboot.application.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import static com.aggrey.springboot.application.demo.security.ApplicationUserPermission.*;

import static com.aggrey.springboot.application.demo.security.ApplicationUserRoles.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private ApplicationUserPermission applicationUserPermission;

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
               // .antMatchers("/api/**").hasRole(STUDENT.name())
         /**       .antMatchers(HttpMethod.DELETE,"/api/v1/students/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/api/v1/students/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/api/v1/students/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/api/v1/students/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                .antMatchers(HttpMethod.DELETE,"/api/v1/course/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/api/v1/course/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/api/v1/course/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/api/v1/course/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())**/
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
//                .roles(STUDENT.name()) //ROLE_STUDENT
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails userLisaLisa = User.builder()
                .username("Lisa")
                .password(passwordEncoder.encode("password"))
//                .roles(STUDENT.name()) //ROLE_STUDENT
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails userRayRaymond = User.builder()
                .username("Ray")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();//ROLE_ADMIN  //linda

        UserDetails userAshleyPerkins = User.builder()
                .username("Ashley")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMINTRAINEE.name())
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();//ROLE_ADMIN_TRAINEE   //tom

        return new InMemoryUserDetailsManager(userJohnyCool, userRayRaymond, userAshleyPerkins, userLisaLisa);

    }
}

