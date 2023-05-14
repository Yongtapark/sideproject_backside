package com.backend.fitta.dto.Member;

import com.backend.fitta.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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
    @NotBlank
    private Gender gender;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private LocalDate birthday;
}
