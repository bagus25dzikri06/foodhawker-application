package com.sinaukoding.foodhawker.entity.transaction;

import com.sinaukoding.foodhawker.entity.app.BaseEntity;
import com.sinaukoding.foodhawker.entity.master.Food;
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
@Table(name = "m_order_item", indexes = {
        @Index(name = "idx_order_item_created_date", columnList = "createdDate"),
        @Index(name = "idx_order_item_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_order_item_status", columnList = "status"),
        @Index(name = "idx_order_item_total_price", columnList = "totalPrice"),
        @Index(name = "idx_order_item_id_food", columnList = "id_food")
})
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_food", referencedColumnName = "foodId", nullable = false)
    private Food food;

    @Min(value = 0, message = "Quantity may not be negative")
    @Column(nullable = false)
    private int quantity;

    @Min(value = 0, message = "Total price may not be negative")
    @Column(nullable = false)
    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}