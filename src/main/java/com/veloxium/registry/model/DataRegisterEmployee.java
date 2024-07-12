package com.veloxium.registry.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record DataRegisterEmployee(
        @NotNull
        String name,
        @NotNull
        String cpf,
        @Past
        LocalDate birthDate,
        @NotNull
        @Valid
        DataFunction workspace,
        @Valid
        @NotNull
        DataAddress address
) {
}
