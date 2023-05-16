package com.backend.fitta.dto.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import com.backend.fitta.entity.enums.Gender;

import java.time.LocalDate;

@Data
public class SignUpRequest {

    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    private Gender gender;
    @NotBlank
    private String phoneNumber;
    private LocalDate birthday;
}
