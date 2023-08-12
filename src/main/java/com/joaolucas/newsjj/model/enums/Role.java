package com.joaolucas.newsjj.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER("USER"),
    JOURNALIST("JOURNALIST"),
    ADMIN("ADMIN");

    private final String name;
}
