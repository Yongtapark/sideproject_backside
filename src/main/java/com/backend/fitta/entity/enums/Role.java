package com.backend.fitta.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    OWNER("ROLE_OWNER","사장"),
    MEMBER("ROLE_MEMBER","사용자"),
    STAFF("ROLE_STAFF","직원");

    private final String key;
    private final String title;
}
