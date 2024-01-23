package com.train.food.order.mayu.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.train.food.order.mayu.entity.User;
import com.train.food.order.mayu.entity.UserRegistrationDto;
import com.train.food.order.mayu.repo.UserRepository;
import com.train.food.order.mayu.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository repo;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping("/signup")
	public String showSignupForm(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		String username = registrationDto.getUsername();

		Optional<User> user = repo.findByUsername(username);
		if (user.isPresent() && username.equalsIgnoreCase(user.get().getUsername())) { // &&
																						// !email.equalsIgnoreCase(user.getEmail())
			return "redirect:/signup?failure";
		}
		userService.save(registrationDto);
		return "redirect:/signup?success";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@GetMapping("/")
	public String home() {
		return "redirect:/menu";
	}
//
//	@PostMapping("/login")
//	public String login(@ModelAttribute("user") User user, HttpSession session) {
//		authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication != null) {
//			return "redirect:/menu";
//		} else {
//			return "redirect:/login";
//		}
//	}

//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
////		session.invalidate();
//		return "redirect:/login";
//	}
}
