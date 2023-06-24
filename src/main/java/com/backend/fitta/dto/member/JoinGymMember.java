package com.backend.fitta.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinGymMember {
    private Long memberId;
    private Long gymId;
    private Long[] programIds;
}
