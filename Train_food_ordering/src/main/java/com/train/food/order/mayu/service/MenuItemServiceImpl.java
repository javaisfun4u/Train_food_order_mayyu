package com.train.food.order.mayu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.food.order.mayu.entity.MenuItem;
import com.train.food.order.mayu.repo.MenuItemRepository;

@Service
public class MenuItemServiceImpl implements MenuItemService {

	@Autowired
	private MenuItemRepository menuItemRepository;

	@Override
	public List<MenuItem> getAllMenuItems() {
		return menuItemRepository.findAll();
	}
}
