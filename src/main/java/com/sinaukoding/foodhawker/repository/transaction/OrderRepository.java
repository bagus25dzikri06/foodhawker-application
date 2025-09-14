package com.sinaukoding.foodhawker.repository.transaction;

import com.sinaukoding.foodhawker.entity.transaction.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {
}