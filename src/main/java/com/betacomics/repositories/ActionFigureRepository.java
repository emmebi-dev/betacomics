package com.betacomics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacomics.models.ActionFigure;

@Repository
public interface ActionFigureRepository extends JpaRepository<ActionFigure, Long> {

}
