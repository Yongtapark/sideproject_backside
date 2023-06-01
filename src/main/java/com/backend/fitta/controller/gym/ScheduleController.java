package com.backend.fitta.controller.gym;

import com.backend.fitta.dto.schedule.SaveScheduleRequest;
import com.backend.fitta.dto.schedule.UpdateScheduleRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.service.apiService.interfaces.ScheduleApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Operation(summary = "스케줄 수정 메서드", description = "스케줄의 정보를 수정 할 수 있습니다.")
    @PutMapping("/{scheduleId}")
    public ResponseEntity<Long> updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.ok(scheduleApiService.updateSchedule(scheduleId, request));
    }

    @Operation(summary = "스케줄 삭제 메서드", description = "스케줄을 삭제할 수 있습니다.")
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleApiService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }
}
