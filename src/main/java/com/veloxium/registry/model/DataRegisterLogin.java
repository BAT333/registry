package com.veloxium.registry.model;

public record DataRegisterLogin(
        String login,
        String password,
        String roles
) {
}
