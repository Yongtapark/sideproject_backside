package com.backend.fitta.dto.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.backend.fitta.entity.enums.Gender;

import java.time.LocalDate;

@Data
public class SignUpRequest {


    private String email;

    private String password;

    private String passwordConfirm;


    private String name;

    private String address;
    private Gender gender;

    private String phoneNumber;
    private LocalDate birthday;
}