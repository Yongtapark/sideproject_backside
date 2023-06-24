package com.backend.fitta.dto.member;

import com.backend.fitta.entity.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdateMemberRequest {
    @Email
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String password;
    @NotBlank
    @Pattern(regexp = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$",message = "이름에 공백 혹은 특수 문자가 포함되어 있습니다 .")
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String address;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;
    private Long height;
    private Long weight;
    private String occupation;
    private String note;
    @NotBlank
    @NotNull
    private String phoneNumber;
    @NotNull
    private LocalDate birthdate;
}
