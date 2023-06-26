package com.backend.fitta.entity.gym;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Program {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private String note;
    @OneToMany(mappedBy = "program")
    private List<Registrations> registrations = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    public Program(String name, BigDecimal price, String note) {
        this.name = name;
        this.price = price;
        this.note = note;
    }

    void addProgram(Gym gym){
        this.gym=gym;
        gym.getProgramClass().add(this);
    }

    public Program(String name, BigDecimal price, String note, Gym gym) {
        this.name = name;
        this.price = price;
        this.note = note;
        if(gym!=null){
            addProgram(gym);
        }
    }
}
