package com.backend.fitta.dto.login;

import com.backend.fitta.entity.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    private Role role;
}
