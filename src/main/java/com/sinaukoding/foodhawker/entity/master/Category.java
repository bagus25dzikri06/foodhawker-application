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
@Table(name = "m_category", indexes = {
        @Index(name = "idx_food_created_date", columnList = "createdDate"),
        @Index(name = "idx_food_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_food_status", columnList = "status"),
        @Index(name = "idx_food_name", columnList = "name"),
        @Index(name = "idx_food_id_restaurant", columnList = "id_restaurant")
})
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String categoryId;

    @Size(max = 100, message = "Max karakter 100")
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", referencedColumnName = "restaurantId", nullable = false)
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}