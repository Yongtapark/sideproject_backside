package com.backend.fitta.dto.staff;

import com.backend.fitta.entity.enums.Gender;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class StaffTeamResponse {
    private Long id;
    private String name;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private LocalDate birthdate;

    @QueryProjection
    public StaffTeamResponse(Long id,String name, LocalDate birthdate, Gender gender, String phoneNumber, String address) {
        this.id=id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


}
