package com.backend.fitta.controller.gym.staff;

import com.backend.fitta.dto.team.FindStaffByIdResponse;
import com.backend.fitta.dto.team.SaveStaffRequest;
import com.backend.fitta.dto.team.UpdateStaffRequest;
import com.backend.fitta.service.apiService.interfaces.StaffApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "스태프", description = "스태프 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/staff")
public class StaffController {

    private final StaffApiService staffApiService;

    @Operation(summary = "스태프 등록 메서드", description = "스태프 등록 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveStaff(@Valid @RequestBody SaveStaffRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffApiService.save(request));
    }

    @Operation(summary = "스태프 조회 메서드", description = "스태프 id로 스태프를 조회 할 수 있습니다.")
    @GetMapping("/{staffId}")
    public ResponseEntity<FindStaffByIdResponse> findStaff(@PathVariable long staffId) {
        return ResponseEntity.ok(staffApiService.findById(staffId));
    }

    @Operation(summary = "스태프 정보 수정 메서드", description = "스태프 id로 스태프를 찾아 스태프의 정보를 수정 할 수 있습니다.")
    @PutMapping("/{staffId}")
    public ResponseEntity<Long> updateTeam(@PathVariable Long staffId, @Valid @RequestBody UpdateStaffRequest request) {
        return ResponseEntity.ok(staffApiService.update(staffId, request));
    }

    @Operation(summary = "스태프 삭제 메서드", description = "스태프 id로 스태프를 삭제할 수 있습니다.")
    @DeleteMapping("/{staffId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long staffId) {
        staffApiService.delete(staffId);
        return ResponseEntity.noContent().build();
    }

}



