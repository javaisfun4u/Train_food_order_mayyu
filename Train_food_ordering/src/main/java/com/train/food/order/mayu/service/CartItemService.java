package com.train.food.order.mayu.service;

import java.util.List;

import com.train.food.order.mayu.entity.CartItem;
import com.train.food.order.mayu.entity.MenuItem;
import com.train.food.order.mayu.entity.User;

public interface CartItemService {
    List<CartItem> getCartItemsByUser(User user);
    void addToCart(User user, MenuItem menuItem, int quantity);
	void clearCart(User user); 
}
