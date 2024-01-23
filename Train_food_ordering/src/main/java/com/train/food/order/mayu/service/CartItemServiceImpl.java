package com.train.food.order.mayu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.food.order.mayu.entity.CartItem;
import com.train.food.order.mayu.entity.MenuItem;
import com.train.food.order.mayu.entity.User;
import com.train.food.order.mayu.repo.CartItemRepository;
import com.train.food.order.mayu.repo.MenuItemRepository;
import com.train.food.order.mayu.repo.UserRepository;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private MenuItemRepository menuItemRepository;

	@Override
	public List<CartItem> getCartItemsByUser(User user) {
		return cartItemRepository.findByUser(user);
	}

	@Override
	public void addToCart(User user, MenuItem menuItem, int quantity) {
		// You might want to add validation and error handling here
		CartItem existingCartItem = cartItemRepository.findByUserAndMenuItem(user, menuItem);

		if (existingCartItem != null) {
			existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
		} else {
			CartItem newCartItem = new CartItem();
			newCartItem.setUser(user);
			newCartItem.setMenuItem(menuItem);
			newCartItem.setQuantity(quantity);
			cartItemRepository.save(newCartItem);
		}
	}

	@Override
	public void clearCart(User user) {
		List<CartItem> cartItems = cartItemRepository.findByUser(user);
		cartItemRepository.deleteAll(cartItems);
	}

}