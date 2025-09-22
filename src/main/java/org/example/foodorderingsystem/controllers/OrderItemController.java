package org.example.foodorderingsystem.controllers;

import org.example.foodorderingsystem.entites.OrderItem;
import org.example.foodorderingsystem.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/all")
    public Iterable<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    @PostMapping("/order/{orderId}")
    public OrderItem addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        return orderItemService.addOrderItem(orderId, orderItem);
    }

    @PutMapping("/{id}")
    public OrderItem updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(id, orderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
    }

    @GetMapping("/order/{orderId}")
    public Iterable<OrderItem> getOrderItems(@PathVariable Long orderId) {
        return orderItemService.getOrderItems(orderId);
    }
}