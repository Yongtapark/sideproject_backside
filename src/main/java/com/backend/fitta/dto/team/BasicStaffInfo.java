package com.backend.fitta.dto.team;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.gym.Staff;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BasicStaffInfo {
    private String name;
    private LocalDate birthday;
    private Gender gender;
    private String phoneNumber;
    private String address;

    private String gymName;

    public BasicStaffInfo(Staff staff) {
        this.name = staff.getName();
        this.birthday = staff.getBirthday();
        this.gender = staff.getGender();
        this.phoneNumber = staff.getPhoneNumber();
        this.address = staff.getAddress();
        if (staff.getGym() != null) {
            this.gymName = staff.getGym().getName();
        }

    }
}

