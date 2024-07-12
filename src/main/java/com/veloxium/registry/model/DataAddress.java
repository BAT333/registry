package com.veloxium.registry.model;

import jakarta.validation.constraints.NotNull;

public record DataAddress(
        @NotNull
        String zipCode,
        String neighborhood,
        @NotNull
        String city,
        String complement,
        @NotNull
        String state,
        @NotNull
        String street,
        @NotNull
        Integer number
) {
}
