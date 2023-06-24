package com.backend.fitta.dto.owner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateOwnerRequest {
    @Pattern(regexp = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$", message = "이름에 공백 혹은 특수 문자가 포함되어 있습니다.")
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "전화번호는 숫자만 입력할 수 있습니다.")
    @Size(min = 11,max = 11,message = "전화번호를 다시 입력해 주세요.")
    private String phoneNumber;
    private String address;
    @NotBlank
    private String businessRegistrationNumber;
}
