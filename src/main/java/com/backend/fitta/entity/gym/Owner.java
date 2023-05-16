package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.Auditing;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends Auditing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String BusinessRegistrationNumber;
    @OneToMany(mappedBy = "owner")
    private List<Gym> gym = new ArrayList<>();


    public Owner(String name, String phone, String address, String businessRegistrationNumber) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        BusinessRegistrationNumber = businessRegistrationNumber;
    }


}
