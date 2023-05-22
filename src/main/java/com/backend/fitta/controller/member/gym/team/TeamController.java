package com.backend.fitta.controller.member.gym.team;

import com.backend.fitta.dto.team.FindTeamByIdResponse;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.service.apiService.interfaces.TeamApiService;
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

    private final TeamApiService teamApiService;

    @PostMapping
    public ResponseEntity<Long> saveTeam(@Valid @RequestBody SaveTeamRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamApiService.save(request));
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<FindTeamByIdResponse> findTeam(@PathVariable long teamId) {
        return ResponseEntity.ok(teamApiService.findById(teamId));
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Long> updateTeam(@PathVariable Long teamId, @Valid @RequestBody UpdateTeamRequest request) {
        return ResponseEntity.ok(teamApiService.updateTeam(teamId, request));
    }


    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long teamId) {
        teamApiService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }



}
