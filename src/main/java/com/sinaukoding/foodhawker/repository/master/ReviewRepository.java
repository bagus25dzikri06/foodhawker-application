package com.sinaukoding.foodhawker.repository.master;

import com.sinaukoding.foodhawker.entity.master.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReviewRepository extends JpaRepository<Review, String>, JpaSpecificationExecutor<Review> {
}