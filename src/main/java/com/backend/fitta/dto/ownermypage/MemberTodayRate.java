package com.backend.fitta.dto.ownermypage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberTodayRate {
    private Double maleRate;
    private Double femaleRate;
    private Long maleCount;
    private Long femaleCount;
    private Long totalCount;
}
