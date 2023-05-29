package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import lombok.Data;

import java.util.List;

@Data
public class BasicGymInfo {
    private String name;
    private String ownerName;
    private String phoneNumber;
    private String address;
    private GenderDivision genderDivision;
    private List<MemberGymResponse> members;
    private List<StaffGymResponse> staffs;

    public BasicGymInfo(Gym gym) {
        this.name = gym.getName();
        this.ownerName = gym.getOwner().getName();
        this.phoneNumber = gym.getPhoneNumber();
        this.address = gym.getAddress();
        this.genderDivision = gym.getGenderDivision();
    }
}
