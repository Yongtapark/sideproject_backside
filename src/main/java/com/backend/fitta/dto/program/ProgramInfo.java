package com.backend.fitta.dto.program;

import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Registrations;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class ProgramInfo {
    private Long id;
    private Long gymId;
    private String name;
    private BigDecimal price;
    private String note;

}
