package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.Auditing;
import com.backend.fitta.entity.enums.GenderDivision;
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
public class Gym extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Owner owner;
    private String name;
    private String phoneNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private GenderDivision genderDivision;
    @OneToMany(mappedBy = "gym")
    private List<Staff> staff =new ArrayList<>();
    @OneToMany(mappedBy = "gym")
    private List<Member> member =new ArrayList<>();

    public Gym(String name, Owner owner, String phoneNumber, String address, GenderDivision genderDivision) {
        this.name = name;
        if (owner != null) {
            changeOwner(owner);
        }
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.genderDivision = genderDivision;
    }

    public void changeGymInfo(String name, String phoneNumber, String address, GenderDivision genderDivision) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.genderDivision = genderDivision;
    }

    public void changeOwner(Owner owner){
        this.owner=owner;
        owner.getGym().add(this);
    }
}
