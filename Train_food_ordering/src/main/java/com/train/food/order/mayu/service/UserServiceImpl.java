package com.train.food.order.mayu.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.train.food.order.mayu.entity.Role;
import com.train.food.order.mayu.entity.User;
import com.train.food.order.mayu.entity.UserRegistrationDto;
import com.train.food.order.mayu.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

//    @Override
//    public User signUp(User user) {
//        // You might want to add validation and error handling here
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User login(String username, String password) {
//        // You might want to add authentication logic here
//        return userRepository.findByUsername(username)
//                .filter(u -> u.getPassword().equals(password))
//                .orElse(null);
//    }

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getUsername(), passwordEncoder.encode(registrationDto.getPassword()),
				Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
				user.get().getPassword(), mapRolesToAuthorities(user.get().getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
