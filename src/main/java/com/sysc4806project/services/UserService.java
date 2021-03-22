package com.sysc4806project.services;

import com.sysc4806project.models.User;
import com.sysc4806project.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User registerUser(UserRegistrationDto registrationDto);

    User findByUsername(String username);
}