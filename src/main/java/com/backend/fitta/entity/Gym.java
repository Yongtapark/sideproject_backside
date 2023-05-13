package com.backend.fitta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String owner;
    private String phone;
    private String address;
    private GenderDivision genderDivision;

    public Gym( String name, String owner, String phone, String address, GenderDivision genderDivision) {
        this.name = name;
        this.owner = owner;
        this.phone = phone;
        this.address = address;
        this.genderDivision = genderDivision;
    }
}
