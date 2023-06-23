package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private Member member;

    @ManyToOne
    private Program program;


    private LocalDate registDate;

    public Registrations(Member member, Program program) {
        this.member = member;
        this.program = program;
        this.registDate = LocalDate.now();
    }
}
