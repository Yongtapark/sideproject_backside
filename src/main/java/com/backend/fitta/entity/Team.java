package com.backend.fitta.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Team {
    @Column(name = "team_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList();
    @OneToMany(mappedBy = "team")
    private List<Staff> staffs =new ArrayList<>();
}
