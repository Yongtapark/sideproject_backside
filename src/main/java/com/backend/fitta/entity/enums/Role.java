package com.backend.fitta.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_OWNER","사장"),
    USER("ROLE_MEMBER","사용자");

    private final String key;
    private final String title;
}
