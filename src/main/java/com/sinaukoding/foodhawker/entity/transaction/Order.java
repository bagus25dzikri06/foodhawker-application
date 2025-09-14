package com.sinaukoding.foodhawker.entity.transaction;

import com.sinaukoding.foodhawker.entity.app.BaseEntity;
import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.OrderStatus;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Table(name = "m_order", indexes = {
        @Index(name = "idx_order_created_date", columnList = "createdDate"),
        @Index(name = "idx_order_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_order_status", columnList = "status"),
        @Index(name = "idx_order_orderStatus", columnList = "orderStatus"),
        @Index(name = "idx_order_id_customer", columnList = "id_customer")
})
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", referencedColumnName = "restaurantId", nullable = false)
    private Restaurant restaurant;

    @Min(value = 0, message = "Total amount may not be negative")
    @Column(nullable = false)
    private Long totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}