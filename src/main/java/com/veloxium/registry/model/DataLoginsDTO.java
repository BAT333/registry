package com.veloxium.registry.model;

import jakarta.validation.constraints.NotNull;

public record DataLoginsDTO(
        Long id,
        @NotNull
        String login,
        @NotNull
        String password
) {
}
