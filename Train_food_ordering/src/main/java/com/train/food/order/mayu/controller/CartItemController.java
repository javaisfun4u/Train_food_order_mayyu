package com.train.food.order.mayu.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.train.food.order.mayu.entity.CartItem;
import com.train.food.order.mayu.entity.MenuItem;
import com.train.food.order.mayu.entity.User;
import com.train.food.order.mayu.repo.MenuItemRepository;
import com.train.food.order.mayu.repo.UserRepository;
import com.train.food.order.mayu.service.CartItemService;

@Controller
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private MenuItemRepository menuItemRepository; // Inject MenuItemRepository

	@Autowired
	private UserRepository userRepository; // Inject MenuItemRepository

	Authentication authentication;

	@GetMapping("/cart")
	public String showCart(HttpSession session, Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

		if (loggedInUser != null) {
			String username = loggedInUser.getName();

			Optional<User> user = userRepository.findByUsername(username);
			List<CartItem> cartItems = cartItemService.getCartItemsByUser(user.get());
			model.addAttribute("cartItems", cartItems);
		}
		return "cart";
	}

	@PostMapping("/addToCart")
	public String addToCart(@RequestParam Long itemId, @RequestParam int quantity, HttpSession session) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

		if (loggedInUser != null) {
			String username = loggedInUser.getName();

			Optional<User> user = userRepository.findByUsername(username);
			MenuItem menuItem = menuItemRepository.findById(itemId).orElse(null);

			if (menuItem != null && user.isPresent()) {
				cartItemService.addToCart(user.get(), menuItem, quantity);
			}
		}
		// Use MenuItemRepository to get MenuItem by ID
		return "redirect:/cart";

	}
}
