package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import lombok.Data;

@Data
public class BasicGymInfoResponse {
    String name;
    String ownerName;
    String phoneNumber;
    String address;

    GenderDivision genderDivision;
}
