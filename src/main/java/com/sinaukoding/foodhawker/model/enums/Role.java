package com.sinaukoding.foodhawker.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    CUSTOMER("Customer"),
    FOOD_VENDOR("Food Vendor"),
    ADMIN("Admin");

    private final String label;

    Role(String label) {
        this.label = label;
    }
}