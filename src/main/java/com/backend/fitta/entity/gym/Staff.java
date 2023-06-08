package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.Auditing;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Staff extends Auditing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthdate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phoneNumber;
    private String address;
    private Role role=Role.STAFF;
    @Enumerated(EnumType.STRING)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @OneToOne(mappedBy = "staff")
    private Schedule schedule;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "schedule_id")
//    private Schedule schedule;
    public Staff(String name, LocalDate birthdate, Gender gender, String phoneNumber, String address, Gym gym, Team team) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;

        if (gym != null) {
            changeGym(gym);
        }
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeStaffInfo(String name, LocalDate birthdate, String phone, String address) {
        this.name = name;
        this.birthdate = birthdate;
        this.phoneNumber = phone;
        this.address = address;
    }

    public void changeGym(Gym gym){
        this.gym=gym;
        gym.getStaff().add(this);
    }
    /*양방향 연관관계를 위해 this.team = team 이 아닌 changeTeam 메서드와 위의 방식을 사용합니다.
    서로 this.team=team, this.member=member 일 경우에, member.setTeam 을 하더라도, team 객체에는 member 가 들어있지 않습니다.
    그렇기에, 둘중의 한 곳에 양방향 연관관계 설정을 위한 메서드, 즉 한번에 두 객체의 값을 설정하는 메서드를 생성합니다.*/

    public void changeTeam(Team team){
        this.team=team;//staff 객체에 team 을 추가하고
        //team.getStaffs().add(this); // team 객체에도 동시에 staff 의 객체를 추가합니다.
    }


}
