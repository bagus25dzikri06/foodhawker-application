package com.sinaukoding.foodhawker.repository.master;

import com.sinaukoding.foodhawker.entity.master.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FoodRepository extends JpaRepository<Food, String>, JpaSpecificationExecutor<Food> {

}