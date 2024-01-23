package com.train.food.order.mayu.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.train.food.order.mayu.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}