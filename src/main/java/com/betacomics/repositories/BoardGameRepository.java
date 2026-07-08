package com.betacomics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacomics.models.BoardGame;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {

}
