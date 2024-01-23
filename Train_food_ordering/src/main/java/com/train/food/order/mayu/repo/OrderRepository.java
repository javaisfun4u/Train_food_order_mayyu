package com.train.food.order.mayu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.train.food.order.mayu.entity.OrderEntity;
import com.train.food.order.mayu.entity.User;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	List<OrderEntity> findByUser(User user);
}