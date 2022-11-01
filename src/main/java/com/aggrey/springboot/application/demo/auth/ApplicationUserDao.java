package com.aggrey.springboot.application.demo.auth;

import org.springframework.context.annotation.Bean;

import java.util.Optional;

public interface ApplicationUserDao {
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
