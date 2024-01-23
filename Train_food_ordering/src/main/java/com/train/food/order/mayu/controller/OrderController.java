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
import com.train.food.order.mayu.entity.OrderEntity;
import com.train.food.order.mayu.entity.User;
import com.train.food.order.mayu.repo.UserRepository;
import com.train.food.order.mayu.service.CartItemService;
import com.train.food.order.mayu.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserRepository userRepository;
	

//	@PostMapping("/placeOrder")
//	public String placeOrder(@RequestParam String pnrNumber, HttpSession session) {
////		User user = (User) session.getAttribute("user");
//		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//		Optional<User> user =null;
//		if (loggedInUser != null) {
//			String username = loggedInUser.getName();
//
//			 user = userRepository.findByUsername(username);
//		}
//		List<CartItem> cartItems = cartItemService.getCartItemsByUser(user.get());
//
//		if (!cartItems.isEmpty() && user!=null) {
//			OrderEntity order = orderService.placeOrder(user.get(), cartItems, pnrNumber);
//			// Clear the cart after placing the order
//			cartItemService.clearCart(user.get());
//			return "redirect:/orderHistory";
//		
//		} else {
//			// Handle empty cart
//			return "redirect:/cart";
//		}
//	}
	
	@PostMapping("/placeOrder")
	public String placeOrder(@RequestParam String pnrNumber, HttpSession session) {
	    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    Optional<User> user = null;

	    if (loggedInUser != null) {
	        String username = loggedInUser.getName();
	        user = userRepository.findByUsername(username);
	    }

	    if (user != null && user.isPresent()) {
	        List<CartItem> cartItems = cartItemService.getCartItemsByUser(user.get());

	        if (!cartItems.isEmpty()) {
	            OrderEntity order = orderService.placeOrder(user.get(), cartItems, pnrNumber);
	            // Clear the cart after placing the order
	            cartItemService.clearCart(user.get());
	            return "redirect:/orderHistory";
	        } else {
	            return "redirect:/cart";
	        }
	    } else {
	        return "redirect:/login"; // You can redirect to a login page or handle it in another way
	    }
	}


	@GetMapping("/orderHistory")
	public String showOrderHistory(HttpSession session, Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> user =null;
		if (loggedInUser != null ) {
			String username = loggedInUser.getName();

			 user = userRepository.findByUsername(username);
		}
		List<OrderEntity> orders = orderService.getOrdersByUser(user.get());
		model.addAttribute("orders", orders);
		return "orderHistory";
	}
}
