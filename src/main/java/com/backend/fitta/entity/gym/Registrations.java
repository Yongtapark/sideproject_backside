package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Registrations {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;


    private LocalDate registDate;

    public Registrations(Member member, Program program) {
        this.member = member;
        this.program = program;
        this.registDate = LocalDate.now();
    }


}
