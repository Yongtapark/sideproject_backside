package com.backend.fitta.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Staff extends Auditing{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long age;
    private String phone;
    private String address;
    private Grade grade;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_name")
    private Team team;

    public Staff(String name, Long age, String phone, String address, Grade grade, Team team) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.grade = grade;
        if(team !=null){
            changeTeam(team);
        }
    }
    /*양방향 연관관계를 위해 this.team = team 이 아닌 changeTeam 메서드와 위의 방식을 사용합니다.
    서로 this.team=team, this.member=member 일 경우에, member.setTeam 을 하더라도, team 객체에는 member 가 들어있지 않습니다.
    그렇기에, 둘중의 한 곳에 양방향 연관관계 설정을 위한 메서드, 즉 한번에 두 객체의 값을 설정하는 메서드를 생성합니다.*/
    public void changeTeam(Team team){
        this.team=team;//staff 객체에 team 을 추가하고
        team.getStaffs().add(this); // team 객체에도 동시에 staff 의 객체를 추가합니다.
    }


}