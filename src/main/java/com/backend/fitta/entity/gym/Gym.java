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
    private String phone;
    private String address;
    private GenderDivision genderDivision;
    @OneToMany(mappedBy = "gym")
    private List<Staff> staff =new ArrayList<>();
    @OneToMany(mappedBy = "gym")
    private List<Member> member =new ArrayList<>();

    public Gym( String name, Owner owner, String phone, String address, GenderDivision genderDivision) {
        this.name = name;
        this.owner = owner;
        this.phone = phone;
        this.address = address;
        this.genderDivision = genderDivision;
    }
}
