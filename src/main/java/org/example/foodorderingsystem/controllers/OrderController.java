package org.example.foodorderingsystem.controllers;

import org.example.foodorderingsystem.entites.Order;
import org.example.foodorderingsystem.entites.OrderItem;
import org.example.foodorderingsystem.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public Iterable<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order placeOrder(@RequestBody Map<String, Object> orderRequest) {
        Order order = new Order();
        order.setUserId(Long.valueOf(orderRequest.get("userId").toString()));
        order.setRestaurantId(Long.valueOf(orderRequest.get("restaurantId").toString()));

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> itemsData = (List<Map<String, Object>>) orderRequest.get("items");
        List<OrderItem> items = itemsData.stream()
                .map(itemData -> {
                    OrderItem item = new OrderItem();
                    item.setMenuItemId(Long.valueOf(itemData.get("menuItemId").toString()));
                    item.setQuantity(Integer.valueOf(itemData.get("quantity").toString()));
                    return item;
                })
                .toList();

        return orderService.placeOrder(order, items);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        order.setId(id);
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/user/{userId}")
    public Iterable<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Iterable<Order> getOrdersByRestaurant(@PathVariable Long restaurantId) {
        return orderService.getOrdersByRestaurant(restaurantId);
    }

    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        return orderService.updateOrderStatus(id, status);
    }

    @GetMapping("/{id}/total")
    public BigDecimal calculateOrderTotal(@PathVariable Long id) {
        return orderService.calculateOrderTotal(id);
    }
}