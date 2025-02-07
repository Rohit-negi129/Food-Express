package com.FinalProject.OrderService.Controller;

import com.FinalProject.OrderService.Domain.Order;
import com.FinalProject.OrderService.Exception.OrderAlreadyExistsException;
import com.FinalProject.OrderService.Exception.OrderNotFoundException;
import com.FinalProject.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (OrderAlreadyExistsException e) {
            return new ResponseEntity<>("Order with the same ID already exists", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>("No orders found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable int orderId) {
        try {
            Order order = orderService.getOrderById(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>("Order not found with ID: " + orderId, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        try {
            orderService.updateOrder(order);
            return new ResponseEntity<>("Order updated successfully", HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>("Order not found with ID: " + order.getOrderId(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable int orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>("Order not found with ID: " + orderId, HttpStatus.NOT_FOUND);
        }
    }
}

