package com.backend.fitta.repository.staff;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StaffSearchCond {
    long gymId;
    String staffName;

    public StaffSearchCond() {
    }

    public StaffSearchCond(long gymId, String staffName) {
        this.gymId = gymId;
        this.staffName = staffName;
    }
}
