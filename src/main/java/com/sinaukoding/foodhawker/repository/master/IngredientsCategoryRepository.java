package com.sinaukoding.foodhawker.repository.master;

import com.sinaukoding.foodhawker.entity.master.IngredientsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IngredientsCategoryRepository extends JpaRepository<IngredientsCategory, String>, JpaSpecificationExecutor<IngredientsCategory> {

}