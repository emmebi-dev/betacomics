package com.betacomics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacomics.models.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>{

}
