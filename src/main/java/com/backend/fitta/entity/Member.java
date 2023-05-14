package com.backend.fitta.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member {
    public Member() {
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private int age;
    private String address;
    private Gender gender;

    private Integer height;

    private Integer weight;

    private String occupation;
    private String note;
    private String phoneNumber;
    private LocalDate birthday;

    public Member(String email, String name, int age, String address, Gender gender
            , Integer height, Integer weight, String occupation, String note, String phoneNumber, LocalDate birthday) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.occupation = occupation;
        this.note = note;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }
}
