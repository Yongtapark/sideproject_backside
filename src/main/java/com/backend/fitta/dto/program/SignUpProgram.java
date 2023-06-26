package com.backend.fitta.dto.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpProgram {
    private Long gymId;
    private String name;
    private BigDecimal price;
    private String note;

}
