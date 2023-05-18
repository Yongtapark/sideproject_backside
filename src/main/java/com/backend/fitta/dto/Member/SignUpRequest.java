package com.backend.fitta.dto.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$",message = "이름에 공백 혹은 특수 문자가 포함되어 있습니다 .")
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
