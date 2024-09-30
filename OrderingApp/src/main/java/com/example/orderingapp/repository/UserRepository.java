package com.example.orderingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orderingapp.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
	User findByUsername(String username);
}
