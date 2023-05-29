package com.backend.fitta.dto.owner;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FindOwnerByIdResponse {

    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private String businessRegistrationNumber;
    private List<GymOwnerResponse> gyms = new ArrayList<>();

    public FindOwnerByIdResponse(String email, String name, String phoneNumber, String address, String businessRegistrationNumber, List<GymOwnerResponse> gyms) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.gyms = gyms;
    }
}
