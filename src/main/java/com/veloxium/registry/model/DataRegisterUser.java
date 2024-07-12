package com.veloxium.registry.model;

import java.time.LocalDate;

public record DataRegisterUser(
        Long id,
        String name,
        String email,
        LocalDate birthDate
) {
}
