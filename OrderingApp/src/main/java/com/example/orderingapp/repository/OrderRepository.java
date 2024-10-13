package com.example.orderingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orderingapp.model.CustomerOrder;

@Repository
public interface OrderRepository extends JpaRepository <CustomerOrder, Long> {

}
