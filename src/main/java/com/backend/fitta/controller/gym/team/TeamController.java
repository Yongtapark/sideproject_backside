package com.backend.fitta.controller.gym.team;

import com.backend.fitta.dto.team.FindTeamByIdResponse;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.service.apiService.interfaces.TeamApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "팀", description = "팀 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/team")
public class TeamController {

    private final TeamApiService teamApiService;

    @Operation(summary = "팀 추가 메서드", description = "팀 추가 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveTeam(@Valid @RequestBody SaveTeamRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamApiService.save(request));
    }


    @Operation(summary = "팀 조회 메서드", description = "팀 id로 팀 정보를 조회 할 수 있습니다.")
    @GetMapping("/{teamId}")
    public ResponseEntity<FindTeamByIdResponse> findTeam(@PathVariable long teamId) {
        return ResponseEntity.ok(teamApiService.findById(teamId));
    }

    @Operation(summary = "팀 정보 수정 메서드", description = "팀 id로 팀 정보를 찾아 팀의 정보를 수정 할 수 있습니다.")
    @PutMapping("/{teamId}")
    public ResponseEntity<Long> updateTeam(@PathVariable Long teamId, @Valid @RequestBody UpdateTeamRequest request) {
        return ResponseEntity.ok(teamApiService.updateTeam(teamId, request));
    }

    @Operation(summary = "팀 삭제 메서드", description = "팀 id로 팀을 삭제할 수 있습니다.")
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long teamId) {
        teamApiService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }



}
