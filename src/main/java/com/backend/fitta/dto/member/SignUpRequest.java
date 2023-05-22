package com.backend.fitta.dto.member;

import com.backend.fitta.entity.gym.Team;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$", message = "이름에 공백 혹은 특수 문자가 포함되어 있습니다.")
    private String name;
    @NotBlank
    private String address;
    @NotNull
    private Gender gender;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "전화번호는 숫자만 입력할 수 있습니다.")
    @Size(min = 11,max = 11,message = "전화번호를 다시 입력해 주세요.")
    private String phoneNumber;
    @NotNull
    private LocalDate birthday;
    @NotBlank
    @Pattern(regexp = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$", message = "직업에 공백 혹은 특수 문자가 포함되어 있습니다.")
    private String occupation;

}
