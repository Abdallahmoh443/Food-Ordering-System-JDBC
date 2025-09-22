package org.example.foodorderingsystem.services;

import org.example.foodorderingsystem.entites.OrderItem;
import org.example.foodorderingsystem.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem addOrderItem(Long orderId, OrderItem item) {
        item.setOrderId(orderId);
        return orderItemRepository.save(item);
    }

    public Iterable<OrderItem> getOrderItems(Long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        Iterable<OrderItem> allOrderItems = orderItemRepository.findAll();

        for (OrderItem item : allOrderItems) {
            if (item.getOrderId().equals(orderId)) {
                orderItems.add(item);
            }
        }
        return orderItems;
    }

    public OrderItem updateOrderItem(Long id, OrderItem updatedItem) {
        OrderItem existingItem = orderItemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            if (updatedItem.getQuantity() != null) {
                existingItem.setQuantity(updatedItem.getQuantity());
            }
            if (updatedItem.getMenuItemId() != null) {
                existingItem.setMenuItemId(updatedItem.getMenuItemId());
            }
            if (updatedItem.getOrderId() != null) {
                existingItem.setOrderId(updatedItem.getOrderId());
            }
            return orderItemRepository.save(existingItem);
        }
        return null;
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    public Iterable<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
}