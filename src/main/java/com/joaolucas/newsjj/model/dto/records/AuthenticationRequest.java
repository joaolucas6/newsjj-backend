package com.joaolucas.newsjj.model.dto.records;

public record AuthenticationRequest(
        String username,
        String password
) {
}
