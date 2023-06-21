package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class GymProfileInfo {
    private Long id;
    private String name;
    private String ownerName;
    private String phoneNumber;
    private String address;
    private GenderDivision genderDivision;
    private List<String> imageUrls;

    public GymProfileInfo(Gym gym) {
        this.id = gym.getId();
        this.name = gym.getName();
        this.ownerName = gym.getOwner().getName();
        this.phoneNumber = gym.getPhoneNumber();
        this.address = gym.getAddress();
        this.genderDivision = gym.getGenderDivision();
        this.imageUrls = gym.getImage().stream()
                .map(image -> new String(image.getStoreName()))
                .collect(Collectors.toList());
    }
}
