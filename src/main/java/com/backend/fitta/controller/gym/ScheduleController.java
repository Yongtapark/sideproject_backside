package com.backend.fitta.controller.gym;

import com.backend.fitta.dto.schedule.SaveScheduleRequest;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.service.apiService.interfaces.ScheduleApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "스케줄", description = "스케줄 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleApiService scheduleApiService;

    @Operation(summary = "스케줄 추가 메서드", description = "스케줄 추가 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveTeam(@Valid @RequestBody SaveScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleApiService.save(request));
    }

}
