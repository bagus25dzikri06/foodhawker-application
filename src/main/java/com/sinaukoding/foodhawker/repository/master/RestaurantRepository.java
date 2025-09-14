package com.sinaukoding.foodhawker.repository.master;

import com.sinaukoding.foodhawker.entity.master.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RestaurantRepository extends JpaRepository<Restaurant, String>, JpaSpecificationExecutor<Restaurant> {

}