package com.backend.fitta.dto.gym;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerAllGymInfoResponse {
    private Long GymCount;
    private Long TeamCount;
    private Long MemberCount;
}
