package com.backend.fitta.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GYM("ROLE_GYM","GYM"),
    USER("ROLE_MEMBER","MEMBER");

    private final String key;
    private final String title;
}
