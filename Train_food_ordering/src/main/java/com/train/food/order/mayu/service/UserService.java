package com.train.food.order.mayu.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.train.food.order.mayu.entity.User;
import com.train.food.order.mayu.entity.UserRegistrationDto;

public interface UserService extends UserDetailsService{
//    User signUp(User user);
//    User login(String username, String password);
		User save(UserRegistrationDto registrationDto);
}
