package org.example.foodorderingsystem.entites;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("Restaurant")
public class Restaurant {

    @Id
    private Long id;
    private String name;
    private String address;
    private BigDecimal rating; // between 0 and 5

    public Restaurant() {}

    public Restaurant(Long id, String name, String address, BigDecimal rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}
