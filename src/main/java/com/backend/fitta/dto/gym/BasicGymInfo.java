package com.backend.fitta.dto.gym;

import com.backend.fitta.dto.staff.BasicStaffInfo;
import com.backend.fitta.dto.team.SimpleMemberInfo;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BasicGymInfo {
    private Long id;
    private String name;

    private String ownerName;

    private String phoneNumber;

    private String address;

    private GenderDivision genderDivision;
    private List<String> imageUrls;
    private List<SimpleMemberInfo> members;
    private List<BasicStaffInfo> staffs;

    public BasicGymInfo(Gym gym) {
        this.id=gym.getId();
        this.name = gym.getName();
        if (gym.getOwner() != null) {
            this.ownerName = gym.getOwner().getName();
        }
        this.phoneNumber = gym.getPhoneNumber();
        this.address = gym.getAddress();
        this.genderDivision = gym.getGenderDivision();
        this.members = gym.getMember().stream()
                .map(member -> new SimpleMemberInfo(member))
                .collect(Collectors.toList());
        this.staffs = gym.getStaff().stream()
                .map(staff -> new BasicStaffInfo(staff))
                .collect(Collectors.toList());
    }
}
