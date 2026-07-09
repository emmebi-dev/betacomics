package com.betacomics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacomics.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
