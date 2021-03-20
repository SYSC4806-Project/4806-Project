package com.sysc4806project.services;

import com.sysc4806project.models.User;
import com.sysc4806project.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerUser(UserRegistrationDto registrationDto);
}
