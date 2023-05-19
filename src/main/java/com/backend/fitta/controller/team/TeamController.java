package com.backend.fitta.controller.team;

import com.backend.fitta.dto.team.FindTeamByIdResponse;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.exception.TeamNotFoundException;
import com.backend.fitta.service.interfaces.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<Long> saveTeam(@Valid @RequestBody SaveTeamRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.save(request));
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<FindTeamByIdResponse> findTeam(@PathVariable long teamId) {
        validateExistTeam(teamId);
        return ResponseEntity.ok(teamService.findById(teamId).get());
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Long> updateTeam(@PathVariable Long teamId, @Valid @RequestBody UpdateTeamRequest request) {
        validateExistTeam(teamId);
        return ResponseEntity.ok(teamService.updateTeam(teamId, request));
    }


    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long teamId) {
        validateExistTeam(teamId);
        teamService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }

    private void validateExistTeam(Long teamId) {
        teamService.findById(teamId).orElseThrow(() -> new TeamNotFoundException());
    }


}
