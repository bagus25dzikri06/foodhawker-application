package com.sinaukoding.foodhawker.entity.master;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinaukoding.foodhawker.entity.app.BaseEntity;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_ingredients_category", indexes = {
        @Index(name = "idx_ingredients_category_created_date", columnList = "createdDate"),
        @Index(name = "idx_ingredients_category_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_ingredients_category_status", columnList = "status"),
        @Index(name = "idx_ingredients_category_name", columnList = "name"),
        @Index(name = "idx_ingredients_category_id_restaurant", columnList = "id_restaurant")
})
public class IngredientsCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ingredientsCategoryId;

    @Size(max = 100, message = "Max karakter 100")
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", referencedColumnName = "restaurantId", nullable = false)
    private Restaurant restaurant;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<IngredientsItem> ingredients= new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}