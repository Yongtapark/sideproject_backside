package com.backend.fitta.entity.member;


import com.backend.fitta.entity.gym.*;
import com.backend.fitta.entity.utils.Auditing;
import com.backend.fitta.entity.utils.Users;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Auditing implements UserDetails, Users {
    //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String name;
    @Lob
    private String profileImage;
    private LocalDate birthdate;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    @OneToMany(mappedBy = "member")
    private List<Registrations> registrations = new ArrayList<>();
    private Role role = Role.MEMBER;
    //체육관 등록일
    private LocalDate gymJoinDate;
    //결제일
    private LocalDate subscribeDate;
    //만료일
    private LocalDate endSubscribeDate;
    //결제여부
    private boolean isSubscribed =false;

    public void joinGym(Gym gym, List<Program> selectedClasses) {
        this.gym = gym;
        this.isSubscribed=true;
        gym.getMember().add(this);
        registerClasses(selectedClasses);
    }


    private void registerClasses(List<Program> selectedClasses) {
        for (Program program : selectedClasses) {
            Registrations registration = new Registrations(this, program);
            this.registrations.add(registration);
            program.getRegistrations().add(registration);
        }
    }





    public Member(String email, String password, String name, LocalDate birthdate, String phoneNumber, String address, Gender gender, Long height, Long weight, String occupation, String note, Gym gym, Team team) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.occupation = occupation;
        this.note = note;
        this.isSubscribed=false;
        if(gym!=null){
            changeGym(gym);
        }
        if(team!=null){
            changeTeam(team);
        }
    }
    @Builder
    public Member(String email, String password, String name, LocalDate birthdate, String phoneNumber, String address, Gender gender, Long height, Long weight, String occupation, String note, Team team, Gym gym,Role role,String profileImage ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.occupation = occupation;
        this.note = note;
        this.team = team;
        this.gym = gym;
        this.isSubscribed = false;
        if(gym!=null){
            changeGym(gym);
        }
        if(team!=null){
            changeTeam(team);
        }
        if(isSubscribed==true){
            subscribe();
        }
        this.role =role;
        this.profileImage=profileImage;
    }

    public void changeGym(Gym gym){
        this.gym=gym;
        gym.getMember().add(this);
        this.gymJoinDate=LocalDate.now();
    }

    public void changeTeam(Team team){
        this.team=team;
        team.getMembers().add(this);
    }

    public void subscribe(){
        this.subscribeDate=LocalDate.now();
        this.endSubscribeDate= subscribeDate.plusMonths(1).minusDays(1);
    }
    public void changeMemberInfo(String email, String password, String name, String profileImage, LocalDate birthdate, String phoneNumber, String address, Long height, Long weight, String occupation, String note) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.birthdate = birthdate;
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