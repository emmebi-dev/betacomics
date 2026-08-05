package com.betacomics.repositories;

import com.betacomics.enums.OrderStatus;
import com.betacomics.models.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"items", "items.product"})
    List<Order> findByUserIdOrderByOrderDateDesc(Long userId);

    @EntityGraph(attributePaths = {"items", "items.product"})
    Optional<Order> findByIdAndUserId(Long id, Long userId);
    
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);
    
    @Query("SELECT o FROM Order o WHERE o.user.username = :query OR o.user.email = :query")
    Page<Order> findByUserUsernameOrEmail(@Param("query") String query, Pageable pageable);
    
    @Query("SELECT o FROM Order o WHERE (o.user.username = :query OR o.user.email = :query) AND o.status = :status")
    Page<Order> findByUserUsernameOrEmailAndStatus(
            @Param("query") String query,
            @Param("status") OrderStatus status,
            Pageable pageable);

}