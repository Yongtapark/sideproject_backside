package com.backend.fitta.dto.team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveTeamRequest {

    @NotBlank
    private String name;
    @NotNull
    private Long staffId;

    //????
    public SaveTeamRequest() {
    }
}
