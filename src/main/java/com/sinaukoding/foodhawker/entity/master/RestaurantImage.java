package com.sinaukoding.foodhawker.entity.master;

import com.sinaukoding.foodhawker.entity.app.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "m_restaurant_image", indexes = {
        @Index(name = "idx_restaurant_image_created_date", columnList = "createdDate"),
        @Index(name = "idx_restaurant_image_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_restaurant_image_id_food", columnList = "id_restaurant"),
        @Index(name = "idx_restaurant_image_path", columnList = "path")
})
public class RestaurantImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String path;
}