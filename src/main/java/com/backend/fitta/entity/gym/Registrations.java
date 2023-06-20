package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Registrations {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Classes classes;

    private LocalDate registDate;

    public Registrations(Member member, Classes classes) {
        this.member = member;
        this.classes = classes;
        this.registDate = LocalDate.now();
    }
}
