package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.Gender;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MemberGymResponse {
    private String email;
    private String name;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private LocalDate birthdate;

    @QueryProjection
    public MemberGymResponse(String email, String name, LocalDate birthdate, String phoneNumber, String address, Gender gender) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
    }

}
