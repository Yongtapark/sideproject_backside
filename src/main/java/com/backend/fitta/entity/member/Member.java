package com.backend.fitta.entity.member;


import com.backend.fitta.entity.Auditing;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Auditing implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String name;
    private LocalDate birthday;
    private String phoneNumber;
    private String address;
    @Enumerated(EnumType.STRING)
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
    private Role role;

    public Member(String email, String password, String name, LocalDate birthday, String phoneNumber, String address, Gender gender, Long height, Long weight, String occupation, String note, Gym gym, Team team) {
        this.email = email;
        this.password = password;
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

    @Builder
    public Member(String email, String password, String name, LocalDate birthday, String phoneNumber, String address, Gender gender, Long height, Long weight, String occupation, String note, Team team, Gym gym, Role roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.occupation = occupation;
        this.note = note;
        this.team = team;
        this.gym = gym;
        this.role = roles;
    }
    public void changeMemberInfo(String email, String password, String name, LocalDate birthday, String phoneNumber, String address, Long height, Long weight, String occupation, String note) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.occupation = occupation;
        this.note = note;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role.getKey()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}