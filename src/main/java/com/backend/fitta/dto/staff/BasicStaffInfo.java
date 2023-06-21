package com.backend.fitta.dto.staff;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.staff.Staff;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BasicStaffInfo {
    private Long id;
    private String name;
    private LocalDate birthdate;
    private Gender gender;
    private String phoneNumber;
    private String address;
    private String gymName;
    private Role role;

    public BasicStaffInfo(Staff staff) {
        this.id= staff.getId();
        this.name = staff.getName();
        this.birthdate = staff.getBirthdate();
        this.gender = staff.getGender();
        this.phoneNumber = staff.getPhoneNumber();
        this.address = staff.getAddress();
        if (staff.getGym() != null) {
            this.gymName = staff.getGym().getName();
        }
        this.role=staff.getRole();

    }
}

