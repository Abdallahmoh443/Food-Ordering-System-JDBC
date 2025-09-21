package org.example.foodorderingsystem.repositories;

import org.example.foodorderingsystem.entites.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem,Long> {
}
