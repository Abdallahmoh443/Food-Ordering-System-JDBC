package org.example.foodorderingsystem.repositories;


import org.example.foodorderingsystem.entites.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
}
