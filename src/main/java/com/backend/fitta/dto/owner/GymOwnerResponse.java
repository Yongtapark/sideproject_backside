package com.backend.fitta.dto.owner;

import com.backend.fitta.entity.enums.GenderDivision;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class GymOwnerResponse {
    private String name;
    private String phoneNumber;
    private String address;
    private GenderDivision genderDivision;

    @QueryProjection
    public GymOwnerResponse(String name, String phoneNumber, String address, GenderDivision genderDivision) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.genderDivision = genderDivision;
    }
}
