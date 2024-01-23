package com.train.food.order.mayu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.train.food.order.mayu.entity.CartItem;
import com.train.food.order.mayu.entity.MenuItem;
import com.train.food.order.mayu.entity.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUser(User user);

	@Query(value = "SELECT c FROM CartItem c WHERE c.user = :user AND c.menuItem = :menuItem")
	CartItem findByUserAndMenuItem(User user, MenuItem menuItem);
}