package org.example.foodorderingsystem.services;



import org.example.foodorderingsystem.entites.MenuItem;
import org.example.foodorderingsystem.entites.Order;
import org.example.foodorderingsystem.entites.OrderItem;
import org.example.foodorderingsystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final MenuItemService menuItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderItemService orderItemService,
                        MenuItemService menuItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.menuItemService = menuItemService;
    }

    public Order placeOrder(Order order, List<OrderItem> items) {

        for (OrderItem item : items) {
            if (!menuItemService.isMenuItemAvailable(item.getMenuItemId())) {
                throw new RuntimeException("Menu item with ID " + item.getMenuItemId() + " is not available");
            }
        }


        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }


        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("PENDING");
        }


        Order savedOrder = orderRepository.save(order);


        for (OrderItem item : items) {
            item.setOrderId(savedOrder.getId());
            orderItemService.addOrderItem(savedOrder.getId(), item);
        }

        return savedOrder;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Iterable<Order> getOrdersByUser(Long userId) {
        List<Order> userOrders = new ArrayList<>();
        Iterable<Order> allOrders = orderRepository.findAll();

        for (Order order : allOrders) {
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public Iterable<Order> getOrdersByRestaurant(Long restaurantId) {
        List<Order> restaurantOrders = new ArrayList<>();
        Iterable<Order> allOrders = orderRepository.findAll();

        for (Order order : allOrders) {
            if (order.getRestaurantId().equals(restaurantId)) {
                restaurantOrders.add(order);
            }
        }
        return restaurantOrders;
    }

    public Order updateOrderStatus(Long id, String status) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            existingOrder.setStatus(status);
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {

        Iterable<OrderItem> orderItems = orderItemService.getOrderItems(id);
        for (OrderItem item : orderItems) {
            orderItemService.deleteOrderItem(item.getId());
        }


        orderRepository.deleteById(id);
    }

    public BigDecimal calculateOrderTotal(Long orderId) {
        Iterable<OrderItem> orderItems = orderItemService.getOrderItems(orderId);
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem orderItem : orderItems) {
            MenuItem menuItem = menuItemService.getMenuItemById(orderItem.getMenuItemId());
            if (menuItem != null && menuItem.getPrice() != null) {
                BigDecimal itemTotal = menuItem.getPrice()
                        .multiply(BigDecimal.valueOf(orderItem.getQuantity()));
                total = total.add(itemTotal);
            }
        }

        return total;
    }
}
