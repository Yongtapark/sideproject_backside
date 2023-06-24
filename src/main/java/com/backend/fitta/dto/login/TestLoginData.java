package com.backend.fitta.dto.login;

import com.backend.fitta.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestLoginData {
    Long id;
    Role role;
    String name;
    String profileImage;
}
