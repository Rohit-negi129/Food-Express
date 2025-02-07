package com.FinalProject.OrderService.Service;

import com.FinalProject.OrderService.Domain.Order;
import com.FinalProject.OrderService.Exception.OrderAlreadyExistsException;
import com.FinalProject.OrderService.Exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order) throws OrderAlreadyExistsException;

    List<Order> getAllOrders() throws OrderNotFoundException;

    Order getOrderById(int orderId) throws OrderNotFoundException;

    void updateOrder(Order order) throws OrderNotFoundException;

    void deleteOrder(int orderId) throws OrderNotFoundException;
}
