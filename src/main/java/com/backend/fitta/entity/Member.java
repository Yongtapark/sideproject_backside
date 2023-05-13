package com.backend.fitta.entity;

import jakarta.persistence.*;

@Entity
public class Member {

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
}
