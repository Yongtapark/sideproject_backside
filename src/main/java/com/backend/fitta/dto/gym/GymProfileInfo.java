package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
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
//    @QueryProjection
//    public GymProfileInfo(Long id, String name, String ownerName, String phoneNumber,String address, GenderDivision genderDivision, List<String> imageUrls) {
//        this.id = id;
//        this.name = name;
//        this.ownerName = ownerName;
//        this.phoneNumber = phoneNumber;
//        this.address = address;
//        this.genderDivision = genderDivision;
//        this.imageUrls = imageUrls;
//    }



}
