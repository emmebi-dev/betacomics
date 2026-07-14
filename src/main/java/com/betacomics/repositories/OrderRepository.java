package com.betacomics.repositories;

import com.betacomics.models.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"items", "items.product"})
    List<Order> findByUserIdOrderByOrderDateDesc(Long userId);

    @EntityGraph(attributePaths = {"items", "items.product"})
    Optional<Order> findByIdAndUserId(Long id, Long userId);
}