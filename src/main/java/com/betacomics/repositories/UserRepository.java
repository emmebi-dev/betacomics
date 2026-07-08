package com.betacomics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacomics.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
