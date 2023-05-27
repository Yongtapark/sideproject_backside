package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.Auditing;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Owner extends Auditing {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String businessRegistrationNumber;
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

    public void changeOwnerInfo(String name, String password, String phoneNumber, String address, String businessRegistrationNumber) {
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.businessRegistrationNumber = businessRegistrationNumber;
    }
//    public void changeOwnerInfo(String name, String phoneNumber, String address, String businessRegistrationNumber){
//        this.name = name;
//        this.phoneNumber = phoneNumber;
//        this.address = address;
//        this.BusinessRegistrationNumber = businessRegistrationNumber;
//    }

   /* public void changeOwnerInfo(BasicOwnerInfo basicOwnerInfo){
        this.name = basicOwnerInfo.getName();
        this.phoneNumber = basicOwnerInfo.getPhoneNumber();
        this.address = basicOwnerInfo.getAddress();
        this.BusinessRegistrationNumber = basicOwnerInfo.getBusinessRegistrationNumber();
    }*/




}
