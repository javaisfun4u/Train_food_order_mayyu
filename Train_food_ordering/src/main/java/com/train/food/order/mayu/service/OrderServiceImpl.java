package com.train.food.order.mayu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.food.order.mayu.entity.CartItem;
import com.train.food.order.mayu.entity.OrderEntity;
import com.train.food.order.mayu.entity.User;
import com.train.food.order.mayu.repo.CartItemRepository;
import com.train.food.order.mayu.repo.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

//    @Override
//    public OrderEntity placeOrder(User user, List<CartItem> cartItems, String pnrNumber) {
//        // You might want to add validation and error handling here
//    	OrderEntity order = new OrderEntity();
//        order.setUser(user);
//        order.setPnrNumber(pnrNumber);
//        order.setCartItems(cartItems);
//
//        // Update cart items to link them to the order
//        cartItems.forEach(cartItem -> cartItem.setOrder(order));
//        cartItemRepository.saveAll(cartItems);
//        
//        return orderRepository.save(order);
//    }

    @Override
    public OrderEntity placeOrder(User user, List<CartItem> cartItems, String pnrNumber) {
        // You might want to add validation and error handling here
        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setPnrNumber(pnrNumber);
        
        // Set order reference for each cart item
        cartItems.forEach(cartItem -> cartItem.setOrder(order));

        // Save cart items first to ensure they are persisted
        cartItemRepository.saveAll(cartItems);

        // Set the updated cartItems list to the order
        order.setCartItems(cartItems);

        // Save the order with cascading
        return orderRepository.save(order);
    }
    @Override
    public List<OrderEntity> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
