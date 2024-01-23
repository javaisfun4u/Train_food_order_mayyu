package com.train.food.order.mayu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.train.food.order.mayu.entity.MenuItem;
import com.train.food.order.mayu.service.MenuItemService;

@Controller
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;

	@GetMapping("/menu")
	public String showMenu(Model model) {
		List<MenuItem> menuItems = menuItemService.getAllMenuItems();
		model.addAttribute("menuItems", menuItems);
		return "menu";
	}
}
