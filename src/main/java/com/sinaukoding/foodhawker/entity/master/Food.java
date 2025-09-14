package com.sinaukoding.foodhawker.entity.master;

import com.sinaukoding.foodhawker.entity.app.BaseEntity;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_food", indexes = {
        @Index(name = "idx_food_description", columnList = "description"),
        @Index(name = "idx_food_price", columnList = "price")
})
public class Food extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String foodId;

    @Size(max = 100, message = "Max karakter 100")
    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    private String description;

    @Min(value = 0, message = "Price may not be negative")
    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", referencedColumnName = "categoryId", nullable = false)
    private Category category;

    private boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", referencedColumnName = "restaurantId", nullable = false)
    private Restaurant restaurant;

    private boolean vegetarian;
    private boolean seasonal;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<IngredientsItem> ingredients = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "food",
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private Set<FoodImage> listImage = new HashSet<>();
}