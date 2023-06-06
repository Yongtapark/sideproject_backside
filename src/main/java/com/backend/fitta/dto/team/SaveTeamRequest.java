package com.backend.fitta.dto.team;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveTeamRequest {

    @NotBlank
    private String name;

    //????
    public SaveTeamRequest() {
    }
}
