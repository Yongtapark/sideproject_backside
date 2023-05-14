package com.backend.fitta.entity.member;


import com.backend.fitta.entity.Auditing;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.gym.Team;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Auditing {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private Long age;
    private String address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Long height;
    private Long weight;
    private String occupation;
    private String note;
    private String phoneNumber;
    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_name")
    private Team team;

    public Member(String email, String name, Long age, String address, Gender gender, Long height, Long weight, String occupation, String note, String phoneNumber, LocalDate birthday, Team team) {
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
        if(team!=null){
            changeTeam(team);
        }
    }

    public void changeTeam(Team team){
        this.team=team;
        team.getMembers().add(this);
    }

}
