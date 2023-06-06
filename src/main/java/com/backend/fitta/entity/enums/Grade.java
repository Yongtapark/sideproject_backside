package com.backend.fitta.entity.enums;

public enum Grade {
    OWNER,TRAINER,MANAGER;

    @Override
    public String toString() {
        return "OWNER," +
                "TRAINER," +
                "MANAGER";
    }
}
