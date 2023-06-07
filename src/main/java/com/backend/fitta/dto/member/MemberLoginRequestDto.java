package com.backend.fitta.dto.member;

import com.backend.fitta.entity.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberLoginRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
