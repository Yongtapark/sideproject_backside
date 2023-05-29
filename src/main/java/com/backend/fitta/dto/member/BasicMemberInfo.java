package com.backend.fitta.dto.member;

import com.backend.fitta.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BasicMemberInfo {

    private String email;
    private String password;
    private String name;
    private LocalDate birthday;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private Long height;
    private Long weight;
    private String occupation;
    private String note;
    private String teamName;
    private String gymName;
//    private Team team;
//    private Gym gym;
}
