package com.backend.fitta.dto.member;

import com.backend.fitta.entity.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.backend.fitta.entity.enums.Gender;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {
//
    @Email
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String password;
    @NotBlank
    @Pattern(regexp = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$", message = "이름에 공백 혹은 특수 문자가 포함되어 있습니다.")
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String address;
    @NotNull
    @NotNull
    private Gender gender;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "전화번호는 숫자만 입력할 수 있습니다.")
    @Size(min = 11,max = 11,message = "전화번호를 다시 입력해 주세요.")
    @NotNull
    private String phoneNumber;
    @NotNull
    private LocalDate birthdate;
    @NotBlank
    @Pattern(regexp = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$", message = "직업에 공백 혹은 특수 문자가 포함되어 있습니다.")
    private String occupation;

}
