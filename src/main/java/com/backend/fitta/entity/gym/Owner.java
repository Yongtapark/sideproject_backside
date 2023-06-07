package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.Auditing;
import com.backend.fitta.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends Auditing {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String businessRegistrationNumber;
    private Role role = Role.OWNER;
    @Lob
    private String profileImage;
    @OneToMany(mappedBy = "owner")
    private List<Gym> gym = new ArrayList<>();


    public Owner(String email, String password, String name, String phoneNumber, String address, String businessRegistrationNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.businessRegistrationNumber = businessRegistrationNumber;
    }
    @Builder
    public Owner( String email, String password, String name, String phoneNumber, String address, String businessRegistrationNumber, Role role, String profileImage) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.role = role;
        this.profileImage = profileImage;
    }

    public void changeOwnerInfo(Owner owner) {
        this.name = owner.getName();
        this.password = owner.getPassword();
        this.phoneNumber = owner.getPhoneNumber();
        this.address = owner.getAddress();
        this.businessRegistrationNumber = owner.getBusinessRegistrationNumber();
    }
}
