package com.betacomics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacomics.models.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
