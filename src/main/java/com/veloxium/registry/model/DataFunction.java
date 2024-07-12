package com.veloxium.registry.model;

import com.veloxium.registry.domain.Workspace;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DataFunction(
        @NotNull
        Workspace workspaceName,
        String description,
        BigDecimal salary
) {
}
