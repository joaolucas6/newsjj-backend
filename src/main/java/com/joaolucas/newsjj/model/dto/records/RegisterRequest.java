package com.joaolucas.newsjj.model.dto.records;

public record RegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        String role
) {
}
