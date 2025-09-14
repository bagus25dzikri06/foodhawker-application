package com.sinaukoding.foodhawker.entity.master;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinaukoding.foodhawker.entity.app.BaseEntity;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Table(name = "m_review", indexes = {
        @Index(name = "idx_review_created_date", columnList = "createdDate"),
        @Index(name = "idx_review_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_review_status", columnList = "status"),
        @Index(name = "idx_review_id_customer", columnList = "id_customer"),
        @Index(name = "idx_review_id_restaurant", columnList = "id_restaurant"),
        @Index(name = "idx_review_rating", columnList = "rating")
})
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", referencedColumnName = "restaurantID", nullable = false)
    private Restaurant restaurant;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Min(value = 0, message = "Rating may not be negative")
    @Column(nullable = false)
    private double rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}