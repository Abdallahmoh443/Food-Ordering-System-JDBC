package org.example.foodorderingsystem.controllers;

import org.example.foodorderingsystem.entites.MenuItem;
import org.example.foodorderingsystem.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menuitems")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/all")
    public Iterable<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.addMenuItem(menuItem);
    }

    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return menuItemService.updateMenuItem(id, menuItem);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Iterable<MenuItem> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemsByRestaurant(restaurantId);
    }

    @GetMapping("/restaurant/{restaurantId}/available")
    public Iterable<MenuItem> getAvailableItems(@PathVariable Long restaurantId) {
        return menuItemService.getAvailableItems(restaurantId);
    }

    @GetMapping("/{id}/available")
    public boolean isMenuItemAvailable(@PathVariable Long id) {
        return menuItemService.isMenuItemAvailable(id);
    }
}