package org.example.foodorderingsystem.repositories;

import org.example.foodorderingsystem.entites.Order;
import org.example.foodorderingsystem.entites.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @Query(value = "SELECT * FROM `Order` WHERE user_id = :user_id")
    Iterable<Order> findUserOrders(@Param("user_id") Long id);

}
