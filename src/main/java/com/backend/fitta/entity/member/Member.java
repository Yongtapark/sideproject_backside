package com.backend.fitta.entity.member;


import com.backend.fitta.entity.Auditing;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Team;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Auditing {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private LocalDate birthday;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private Long height;
    private Long weight;
    private String occupation;
    private String note;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    public Member(String email, String name, LocalDate birthday, String phoneNumber, String address, Gender gender, Long height, Long weight, String occupation, String note, Gym gym, Team team) {
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.occupation = occupation;
        this.note = note;
        if(gym!=null){
            changeGym(gym);
        }
        if(team!=null){
            changeTeam(team);
        }
    }
    public void changeGym(Gym gym){
        this.gym=gym;
        gym.getMember().add(this);
    }

    public void changeTeam(Team team){
        this.team=team;
        team.getMembers().add(this);
    }

}
