package com.backend.fitta.dto.owner;

import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.owner.Owner;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BasicOwnerInfo {
    private Long id;
    private String email;
    private String name;
    private String profileImage;
    private String phoneNumber;
    private String address;
    private String businessRegistrationNumber;
    private List<BasicGymInfo> gymList;
    private Role role;

    public BasicOwnerInfo(Owner owner) {
        this.id=owner.getId();
        this.email = owner.getEmail();
        this.name = owner.getName();
        this.profileImage = owner.getProfileImage();
        this.phoneNumber = owner.getPhoneNumber();
        this.address =owner.getAddress();
        this.businessRegistrationNumber=owner.getBusinessRegistrationNumber();
        this.gymList = owner.getGym().stream()
                .map(gym -> new BasicGymInfo(gym))
                .collect(Collectors.toList());
        this.role=owner.getRole();
    }
}
