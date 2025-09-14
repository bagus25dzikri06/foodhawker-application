package com.sinaukoding.foodhawker.repository.transaction;

import com.sinaukoding.foodhawker.entity.transaction.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemRepository extends JpaRepository<OrderItem, String>, JpaSpecificationExecutor<OrderItem> {
}