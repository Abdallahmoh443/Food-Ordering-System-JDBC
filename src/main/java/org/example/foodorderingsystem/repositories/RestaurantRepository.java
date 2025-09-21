package org.example.foodorderingsystem.repositories;

import org.example.foodorderingsystem.entites.MenuItem;
import org.example.foodorderingsystem.entites.Restaurant;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    @Query(value = "select * from `menuitem` where restaurant_id = :res_id")
    Iterable<MenuItem> findMenuItems(@Param("res_id") Long restaurantId);
}
