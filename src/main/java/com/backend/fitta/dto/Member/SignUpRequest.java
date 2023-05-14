package com.backend.fitta.dto.Member;

import com.backend.fitta.entity.Gender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
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
