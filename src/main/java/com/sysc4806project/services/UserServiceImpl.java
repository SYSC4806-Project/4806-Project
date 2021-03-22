package com.sysc4806project.services;

import com.sysc4806project.models.User;
import com.sysc4806project.repositories.UserRepository;
import com.sysc4806project.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User registerUser(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getUsername(),passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getFirstname(), registrationDto.getLastname());


        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}