package com.backend.fitta.dto.owner;

import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.entity.gym.Owner;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BasicOwnerInfo {
    private String email;
    String name;
    String phoneNumber;
    String address;
    String businessRegistrationNumber;
    List<BasicGymInfo> gymList;

    public BasicOwnerInfo(Owner owner) {
        this.email = owner.getEmail();
        this.name = owner.getName();
        this.phoneNumber = owner.getPhoneNumber();
        this.address =owner.getAddress();
        this.businessRegistrationNumber=owner.getBusinessRegistrationNumber();
        this.gymList = owner.getGym().stream()
                .map(gym -> new BasicGymInfo(gym))
                .collect(Collectors.toList());
    }
}
