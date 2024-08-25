package com.alrussy.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alrussy.order_service.model.Order;

public interface OrderRepository extends JpaRepository<Order,String>{
    
}
