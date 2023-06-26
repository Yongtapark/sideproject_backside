package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.entity.utils.Auditing;
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
    @Column(name = "gym_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    private String name;
    @Lob
    private String profileImage;
    @Lob
    private String backgroundImage;
    private String phoneNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private GenderDivision genderDivision;
    @OneToMany(mappedBy = "gym")
    private List<Staff> staff = new ArrayList<>();
    @OneToMany(mappedBy = "gym")
    private List<Member> member = new ArrayList<>();

    private String businessIdentificationNumber;

    public Gym(String name, Owner owner, String profileImage, String backgroundImage, String phoneNumber, String address, GenderDivision genderDivision,String businessIdentificationNumber ) {
        this.name = name;
        if (owner != null) {
            changeOwner(owner);
        }
        this.profileImage = profileImage;
        this.backgroundImage = backgroundImage;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.genderDivision = genderDivision;
        this.businessIdentificationNumber = businessIdentificationNumber;
    }

    public void changeGymInfo(String name, String profileImage, String backgroundImage, String phoneNumber, String address, GenderDivision genderDivision) {
        this.name = name;
        this.profileImage = profileImage;
        this.backgroundImage = backgroundImage;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.genderDivision = genderDivision;
    }

    public void changeOwner(Owner owner) {
        this.owner = owner;
        owner.getGym().add(this);
    }
}
