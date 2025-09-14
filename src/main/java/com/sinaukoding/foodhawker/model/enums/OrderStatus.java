package com.sinaukoding.foodhawker.model.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    WAITING("Waiting"),
    CONFIRMED("Confirmed"),
    READY("Ready"),
    OUT_OF_DELIVERY("Out of Delivery"),
    DELIVERED("Delivery");

    private final String label;

    OrderStatus(String label) {
        this.label = label;
    }
}