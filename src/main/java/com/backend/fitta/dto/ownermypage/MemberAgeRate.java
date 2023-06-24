package com.backend.fitta.dto.ownermypage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberAgeRate {
    private Long teenager;
    private Long twenties;
    private Long thirties;
    private Long forties;
    private Long fifties;
    private Long sixties;
    private Long overSeventies;
    private Double teenagerRate;
    private Double twentiesRate;
    private Double thirtiesRate;
    private Double fortiesRate;
    private Double fiftiesRate;
    private Double sixtiesRate;
    private Double overSeventiesRate;
}
