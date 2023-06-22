package com.backend.fitta.repository.gym;

import lombok.Data;

@Data
public class GymSearchCond {

    private String gymName;

    public GymSearchCond() {
    }

    public GymSearchCond(String gymName) {
        this.gymName = gymName;
    }
}
