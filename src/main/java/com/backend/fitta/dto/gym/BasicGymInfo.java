package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import lombok.Data;

@Data
public class BasicGymInfo {
    String name;
    String ownerName;
    String phoneNumber;
    String address;
    GenderDivision genderDivision;

    public BasicGymInfo(Gym gym) {
        this.name = gym.getName();
        this.ownerName = gym.getOwner().getName();
        this.phoneNumber = gym.getPhoneNumber();
        this.address = gym.getAddress();
        this.genderDivision = gym.getGenderDivision();
    }
}
