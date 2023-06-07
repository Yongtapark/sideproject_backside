package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.Gender;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class StaffGymResponse {
    private String name;
    private Gender gender;
    private String address;
    private String phoneNumber;

    private LocalDate birthday;

    @QueryProjection
    public StaffGymResponse(String name, LocalDate birthday, Gender gender, String phoneNumber, String address) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
