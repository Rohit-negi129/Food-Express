# 🍔 Food Express — Online Food Ordering System

> A full-stack food ordering platform with secure authentication, restaurant management, and order tracking — built with Angular and Spring Boot.

---

## 📌 Overview

Food Express is a full-stack web application that allows customers to browse restaurants, add items to a cart, and place food orders online. The platform includes secure user authentication, a restaurant management panel, and an admin interface — all containerized with Docker for consistent deployment.

This project was developed as a **certification project at NIIT** as part of the Full Stack Java Development course.

---

## 🚀 Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | Angular |
| Backend | Java, Spring Boot |
| Security | Spring Security |
| Database | MySQL |
| Containerization | Docker |
| Tools | Git, Postman, Maven |

---

## ✨ Features

- 🔐 **Secure Authentication** — Login/registration with Spring Security and session management
- 🍽️ **Restaurant Browsing** — Browse available restaurants and their menus
- 🛒 **Shopping Cart** — Add/remove items, update quantities, view totals
- 📦 **Order Management** — Place orders and track order status
- 👨‍💼 **Admin Panel** — Manage restaurants, menus, and users
- 🗄️ **Relational Database** — MySQL schema covering users, restaurants, menu items, and orders
- 🐳 **Docker Support** — Containerized for easy and consistent deployment
- 📱 **Responsive UI** — Mobile-friendly design with Angular

---

## 🏗️ Architecture

```
┌────────────────────────────────────────────────┐
│              Angular Frontend                   │
│  Home │ Restaurant List │ Cart │ Orders │ Admin │
└──────────────────────┬─────────────────────────┘
                       │ HTTP / REST API
┌──────────────────────▼─────────────────────────┐
│            Spring Boot Backend                  │
│   Spring Security │ REST Controllers │ Services │
└──────────────────────┬─────────────────────────┘
                       │ Spring Data JPA
┌──────────────────────▼─────────────────────────┐
│              MySQL Database                     │
│  users │ restaurants │ menu_items │ orders      │
└─────────────────────────────────────────────────┘
         All wrapped in Docker containers
```

---

## 📁 Project Structure

```
food-express/
├── frontend/                    # Angular App
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/
│   │   │   │   ├── home/
│   │   │   │   ├── restaurant-list/
│   │   │   │   ├── cart/
│   │   │   │   ├── orders/
│   │   │   │   └── admin/
│   │   │   ├── services/        # HTTP services
│   │   │   ├── guards/          # Auth guards
│   │   │   └── models/          # TypeScript interfaces
│   │   └── environments/
│   └── angular.json
│
├── backend/                     # Spring Boot
│   ├── src/main/java/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── model/
│   │   │   ├── User.java
│   │   │   ├── Restaurant.java
│   │   │   ├── MenuItem.java
│   │   │   └── Order.java
│   │   └── security/
│   └── src/main/resources/
│       └── application.properties
│
├── docker-compose.yml           # Docker orchestration
├── Dockerfile.backend
├── Dockerfile.frontend
└── README.md


## 🔑 API Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/api/auth/register` | Register new user | Public |
| POST | `/api/auth/login` | User login | Public |
| GET | `/api/restaurants` | List all restaurants | Public |
| GET | `/api/restaurants/{id}/menu` | Get restaurant menu | Public |
| POST | `/api/cart/add` | Add item to cart | JWT Required |
| GET | `/api/cart` | View cart | JWT Required |
| POST | `/api/orders` | Place an order | JWT Required |
| GET | `/api/orders/user` | Get user's orders | JWT Required |
| GET | `/api/admin/orders` | All orders (admin) | Admin Only |

---

## 🗄️ Database Schema

```sql
CREATE TABLE users (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100),
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       ENUM('USER', 'ADMIN') DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE restaurants (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    cuisine  VARCHAR(50),
    location TEXT,
    rating   DECIMAL(2,1)
);

CREATE TABLE menu_items (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id BIGINT REFERENCES restaurants(id),
    name          VARCHAR(100),
    description   TEXT,
    price         DECIMAL(8,2),
    category      VARCHAR(50),
    available     BOOLEAN DEFAULT TRUE
);

CREATE TABLE orders (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT REFERENCES users(id),
    restaurant_id BIGINT REFERENCES restaurants(id),
    total_amount DECIMAL(10,2),
    status       ENUM('PLACED','PREPARING','DELIVERED','CANCELLED') DEFAULT 'PLACED',
    placed_at    TIMESTAMP DEFAULT NOW()
);

## 🎓 Certification

This project was built as part of the **Full Stack Java Development** certification at **NIIT**.

---

## 👨‍💻 Author

**Rohit Negi**
- 📧 rohitnegi129@gmail.com
- 🔗 [LinkedIn](https://linkedin.com/in/rohit-negi)
- 🐙 [GitHub](https://github.com/Rohit-negi129)
