package org.example.foodorderingsystem.entites;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("Order")
public class Order {

    @Id
    private Long id;

    @Column("user_id")
    private Long userId;

    @Column("restaurant_id")
    private Long restaurantId;

    @Column("order_date")
    private LocalDateTime orderDate;

    private String status; // PENDING | DELIVERED | CANCELED

    public Order() {}

    public Order(Long id, Long userId, Long restaurantId, LocalDateTime orderDate, String status) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
