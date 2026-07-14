package com.betacomics.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacomics.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	@EntityGraph(attributePaths = {"items", "items.product"})
    Optional<Cart> findByUserId(Long userId);
}
