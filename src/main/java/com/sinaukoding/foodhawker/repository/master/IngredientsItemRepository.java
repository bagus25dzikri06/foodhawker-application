package com.sinaukoding.foodhawker.repository.master;

import com.sinaukoding.foodhawker.entity.master.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IngredientsItemRepository extends JpaRepository<IngredientsItem, String>, JpaSpecificationExecutor<IngredientsItem> {

}