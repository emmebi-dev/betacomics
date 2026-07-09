package com.betacomics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacomics.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
