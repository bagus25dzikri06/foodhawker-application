package com.sinaukoding.foodhawker.model.enums;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String label;

    Status(String label) {
        this.label = label;
    }
}