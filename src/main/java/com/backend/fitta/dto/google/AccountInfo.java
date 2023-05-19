package com.backend.fitta.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountInfo {
    private String email;
    private String name;
    private String picture;

    public AccountInfo(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }
}
