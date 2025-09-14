package com.sinaukoding.foodhawker.entity.master;

import com.sinaukoding.foodhawker.entity.app.BaseEntity;
import com.sinaukoding.foodhawker.entity.managementuser.User;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_restaurant", indexes = {
        @Index(name = "idx_restaurant_created_date", columnList = "createdDate"),
        @Index(name = "idx_restaurant_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_restaurant_status", columnList = "status"),
        @Index(name = "idx_restaurant_name", columnList = "name"),
        @Index(name = "idx_restaurant_description", columnList = "description"),
        @Index(name = "idx_restaurant_cuisineType", columnList = "cuisineType"),
        @Index(name = "idx_restaurant_openingHours", columnList = "openingHours"),
})
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String restaurantId;

    @Size(max = 100, message = "Max karakter 100")
    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    private String description;

    @Column(nullable = false)
    private String cuisineType;

    @Column(nullable = false)
    private String openingHours;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "restaurant",
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private Set<RestaurantImage> listImage = new HashSet<>();
}