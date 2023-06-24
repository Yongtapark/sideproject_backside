package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.staff.Staff;
import com.backend.fitta.entity.utils.Auditing;
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
public class Team extends Auditing {
    @Column(name = "team_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public Team(String name, Staff staff) {
        this.name = name;
        this.staff = staff;
    }

    public void changeTeamInfo(String name){
        this.name = name;
    }
}
