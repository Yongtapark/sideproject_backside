package com.backend.fitta.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class GenderLate {
    private Double maleRate;
    private Double femaleRate;
    private Long maleCount;
    private Long femaleCount;
    private Long totalCount;
}
