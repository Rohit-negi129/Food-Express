package com.FinalProject.OrderService.Service;

import com.FinalProject.OrderService.Domain.Order;
import com.FinalProject.OrderService.Exception.OrderAlreadyExistsException;
import com.FinalProject.OrderService.Exception.OrderNotFoundException;
import com.FinalProject.OrderService.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) throws OrderAlreadyExistsException {
        Optional<Order> existingOrder = orderRepository.findById(order.getOrderId());
        if (existingOrder.isPresent()) {
            throw new OrderAlreadyExistsException("Order with the same ID already exists");
        }
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    @Override
    public List<Order> getAllOrders() throws OrderNotFoundException {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No orders found");
        }
        return orders;
    }

    @Override
    public Order getOrderById(int orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
    }

    @Override
    public void updateOrder(Order order) throws OrderNotFoundException {
        if (!orderRepository.existsById(order.getOrderId())) {
            throw new OrderNotFoundException("Order not found with ID: " + order.getOrderId());
        }

        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int orderId) throws OrderNotFoundException {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }

        // Delete the order
        orderRepository.deleteById(orderId);
    }
    }

