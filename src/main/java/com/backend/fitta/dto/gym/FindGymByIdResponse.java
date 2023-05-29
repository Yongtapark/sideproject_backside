package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Owner;
import lombok.Getter;

import java.util.List;

@Getter
public class FindGymByIdResponse {

    private String name;
    private String phoneNumber;
    private String address;
    private GenderDivision genderDivision;
    private List<MemberGymResponse> members;
    private List<StaffGymResponse> staffs;

    public FindGymByIdResponse(String name, String phoneNumber, String address, GenderDivision genderDivision, List<MemberGymResponse> members, List<StaffGymResponse> staffs) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.genderDivision = genderDivision;
        this.members = members;
        this.staffs = staffs;
    }
}
