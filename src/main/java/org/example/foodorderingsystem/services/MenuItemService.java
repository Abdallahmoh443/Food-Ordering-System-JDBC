package org.example.foodorderingsystem.services;

import org.example.foodorderingsystem.entites.MenuItem;
import org.example.foodorderingsystem.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem addMenuItem(MenuItem item) {
        return menuItemRepository.save(item);
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    public Iterable<MenuItem> getMenuItemsByRestaurant(Long restaurantId) {
        List<MenuItem> restaurantMenuItems = new ArrayList<>();
        Iterable<MenuItem> allMenuItems = menuItemRepository.findAll();

        for (MenuItem item : allMenuItems) {
            if (item.getRestaurantId().equals(restaurantId)) {
                restaurantMenuItems.add(item);
            }
        }
        return restaurantMenuItems;
    }

    public MenuItem updateMenuItem(Long id, MenuItem updatedItem) {
        MenuItem existingItem = menuItemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            if (updatedItem.getName() != null) {
                existingItem.setName(updatedItem.getName());
            }
            if (updatedItem.getPrice() != null) {
                existingItem.setPrice(updatedItem.getPrice());
            }
            if (updatedItem.getAvailable() != null) {
                existingItem.setAvailable(updatedItem.getAvailable());
            }
            if (updatedItem.getRestaurantId() != null) {
                existingItem.setRestaurantId(updatedItem.getRestaurantId());
            }
            return menuItemRepository.save(existingItem);
        }
        return null;
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    public Iterable<MenuItem> getAvailableItems(Long restaurantId) {
        List<MenuItem> availableItems = new ArrayList<>();
        Iterable<MenuItem> allMenuItems = menuItemRepository.findAll();

        for (MenuItem item : allMenuItems) {
            if (item.getRestaurantId().equals(restaurantId) &&
                    item.getAvailable() != null && item.getAvailable()) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    public Iterable<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public boolean isMenuItemAvailable(Long menuItemId) {
        MenuItem item = menuItemRepository.findById(menuItemId).orElse(null);
        return item != null && item.getAvailable() != null && item.getAvailable();
    }
}