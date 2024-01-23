package com.train.food.order.mayu.service;

import java.util.List;

import com.train.food.order.mayu.entity.CartItem;
import com.train.food.order.mayu.entity.OrderEntity;
import com.train.food.order.mayu.entity.User;

public interface OrderService {
	OrderEntity placeOrder(User user, List<CartItem> cartItems, String pnrNumber);
    List<OrderEntity> getOrdersByUser(User user);
}