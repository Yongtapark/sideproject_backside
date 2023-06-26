package com.backend.fitta.dto.program;

import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Program;
import com.backend.fitta.entity.gym.Registrations;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramInfo {
    private Long id;
    private Long gymId;
    private String name;
    private BigDecimal price;
    private String note;

    public ProgramInfo(Program program) {
        this.id = program.getId();
        this.gymId = program.getGym().getId();
        this.name = program.getName();
        this.price = program.getPrice();
        this.note = program.getNote();
    }
}
