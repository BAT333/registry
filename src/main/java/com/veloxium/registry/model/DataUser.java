package com.veloxium.registry.model;

import com.veloxium.registry.domain.RegisterUser;
import jakarta.persistence.Column;

import java.time.LocalDate;

public record DataUser(

        Long id,
        String name,
        String email,
        LocalDate birthDate
) {
    public DataUser(RegisterUser user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getBirthDate());
    }
}
