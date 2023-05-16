package com.backend.fitta.dto.Member;

import com.backend.fitta.entity.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateMemberRequest {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;

    private Long age;
    @NotBlank
    private String address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Long height;
    private Long weight;
    private String occupation;
    private String note;
    @NotBlank
    private String phoneNumber;
    private LocalDate birthday;
}
