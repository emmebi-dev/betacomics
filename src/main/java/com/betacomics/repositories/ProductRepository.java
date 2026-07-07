package com.betacomics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacomics.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
