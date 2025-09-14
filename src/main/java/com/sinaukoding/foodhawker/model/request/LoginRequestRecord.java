package com.sinaukoding.foodhawker.model.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestRecord(
        @NotBlank String username,
        @NotBlank String password
) {
}