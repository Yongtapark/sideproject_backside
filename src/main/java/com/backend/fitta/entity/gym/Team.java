package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {
    @Column(name = "team_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList();
    @OneToMany(mappedBy = "team")
    private List<Staff> staffs =new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
