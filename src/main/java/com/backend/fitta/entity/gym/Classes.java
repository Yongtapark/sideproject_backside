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
public class Classes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private String note;
    @OneToMany(mappedBy = "classes")
    private List<Registrations> registrations = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    public Classes(String name, BigDecimal price, String note) {
        this.name = name;
        this.price = price;
        this.note = note;
    }

    void addClasses(Gym gym){
        this.gym=gym;
        gym.getClasses().add(this);
    }

    public Classes(String name, BigDecimal price, String note, Gym gym) {
        this.name = name;
        this.price = price;
        this.note = note;
        if(gym!=null){
            addClasses(gym);
        }
    }
}
