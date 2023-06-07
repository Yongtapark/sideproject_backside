package com.backend.fitta.dto.ownermypage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AllGymCount {
    private Long GymCount;
    private Long TeamCount;
    private Long MemberCount;
}
