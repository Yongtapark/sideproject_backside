package com.backend.fitta.dto.member;

import com.backend.fitta.entity.enums.Role;
import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String email;
    private String password;

    private Role role;
}
