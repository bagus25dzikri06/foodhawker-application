package com.sinaukoding.foodhawker.entity.master;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinaukoding.foodhawker.entity.app.BaseEntity;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_ingredients_item", indexes = {
        @Index(name = "idx_ingredients_item_created_date", columnList = "createdDate"),
        @Index(name = "idx_ingredients_item_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_ingredients_item_status", columnList = "status"),
        @Index(name = "idx_ingredients_item_name", columnList = "name")
})
public class IngredientsItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Size(max = 100, message = "Max karakter 100")
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingredients_category", referencedColumnName = "ingredientsCategoryId", nullable = false)
    private IngredientsCategory ingredientsCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", referencedColumnName = "restaurantId", nullable = false)
    private Restaurant restaurant;

    private boolean isInStoke;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}