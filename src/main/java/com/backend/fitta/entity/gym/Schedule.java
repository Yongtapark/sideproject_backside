package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.member.Member;
import com.backend.fitta.entity.staff.Staff;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
    @Column(name = "schedule_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String startTime;
    private String endTime;
    private LocalDate date;

    @OneToMany(mappedBy ="schedule")
    private List<Member> members = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;
//    @OneToMany(mappedBy ="schedule")
//    private List<Staff> staffs = new ArrayList<>();

    public Schedule(String startTime, String endTime, LocalDate date, Staff staff) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.staff = staff;
    }

    public void changeScheduleInfo(String startTime, String endTime, LocalDate date, Staff staff) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.staff = staff;
    }
}
