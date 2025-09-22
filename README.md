# Food Ordering System

A comprehensive RESTful web application built with Spring Boot for managing food orders, restaurants, menu items, and users. This system provides APIs for customers to browse restaurants, view menus, place orders, and for administrators to manage the entire system.

## About This Project

This project is part of my learning journey to master Spring Boot framework and gain deeper knowledge in software development.

## Features

- **User Management**: Create, update, and manage customer and admin accounts
- **Restaurant Management**: Add, update, and delete restaurants with ratings
- **Menu Management**: Manage menu items with availability status and pricing
- **Order Processing**: Place orders, track status, and calculate totals
- **Order Item Management**: Detailed order item tracking with quantities

## Technology Stack

- **Backend**: Spring Boot 3.5.6
- **Database**: MySQL with Spring Data JDBC
- **Java Version**: 17
- **Build Tool**: Maven
- **Architecture**: REST API

## Prerequisites

Before running this application, make sure you have the following installed:

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+ (or use the included Maven Wrapper)

## Setup and Installation

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd Food-Ordering-System
```

### 2. Database Setup

Create a MySQL database and update the connection details in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/food_ordering_system
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 3. Database Schema

Create the following tables in your MySQL database:

```sql
CREATE TABLE User (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20),
    role VARCHAR(20) DEFAULT 'CUSTOMER'
);

CREATE TABLE Restaurant (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address TEXT,
    rating DECIMAL(2,1) CHECK (rating >= 0 AND rating <= 5)
);

CREATE TABLE MenuItem (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    restaurant_id BIGINT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (restaurant_id) REFERENCES Restaurant(id)
);

CREATE TABLE `Order` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    restaurant_id BIGINT,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurant(id)
);

CREATE TABLE OrderItem (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    menu_item_id BIGINT,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES `Order`(id),
    FOREIGN KEY (menu_item_id) REFERENCES MenuItem(id)
);
```

### 4. Run the Application

Using Maven Wrapper (recommended):

```bash
./mvnw spring-boot:run
```

Or using Maven:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

### User Endpoints

- `GET /users` - Get all users
- `GET /users/{id}` - Get user by ID
- `POST /users` - Create new user
- `PUT /users` - Update user
- `DELETE /users/{id}` - Delete user
- `GET /users/{id}/orders` - Get user's orders

### Restaurant Endpoints

- `GET /restaurant/all` - Get all restaurants
- `GET /restaurant/{id}` - Get restaurant by ID
- `POST /restaurant` - Create new restaurant
- `PUT /restaurant` - Update restaurant
- `DELETE /restaurant/delete/{id}` - Delete restaurant
- `GET /restaurant/{id}/menuitems` - Get restaurant's menu items

### Menu Item Endpoints

- `GET /menuitems/all` - Get all menu items
- `GET /menuitems/{id}` - Get menu item by ID
- `POST /menuitems` - Add new menu item
- `PUT /menuitems/{id}` - Update menu item
- `DELETE /menuitems/{id}` - Delete menu item
- `GET /menuitems/restaurant/{restaurantId}` - Get menu items by restaurant
- `GET /menuitems/restaurant/{restaurantId}/available` - Get available items
- `GET /menuitems/{id}/available` - Check if menu item is available

### Order Endpoints

- `GET /orders/all` - Get all orders
- `GET /orders/{id}` - Get order by ID
- `POST /orders` - Place new order
- `PUT /orders/{id}` - Update order
- `DELETE /orders/{id}` - Delete order
- `GET /orders/user/{userId}` - Get orders by user
- `GET /orders/restaurant/{restaurantId}` - Get orders by restaurant
- `PUT /orders/{id}/status?status={status}` - Update order status
- `GET /orders/{id}/total` - Calculate order total

### Order Item Endpoints

- `GET /orderitems/all` - Get all order items
- `GET /orderitems/{id}` - Get order item by ID
- `POST /orderitems/order/{orderId}` - Add item to order
- `PUT /orderitems/{id}` - Update order item
- `DELETE /orderitems/{id}` - Delete order item
- `GET /orderitems/order/{orderId}` - Get items for specific order



## Project Structure

```
src/
├── main/
│   ├── java/org/example/foodorderingsystem/
│   │   ├── controllers/          
│   │   ├── entities/             
│   │   ├── repositories/        
│   │   ├── services/             
│   │   └── FoodOrderingSystemApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/                     # Test files
```

## Configuration

The application uses Spring Boot's default configuration. You can customize settings in `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/food_ordering_system
spring.datasource.username=your_username
spring.datasource.password=your_password

# Server Configuration
server.port=8080

# Logging Configuration
logging.level.org.example.foodorderingsystem=DEBUG
```



## Future Enhancements

- Authentication and Authorization (JWT)
- Payment Integration
- Real-time Order Tracking
- Email Notifications
- Restaurant Analytics Dashboard
- Delivery Tracking
- Rating and Review System

