package com.example.app_web.service;

import com.example.app_web.model.User;
import com.example.app_web.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

}
