package com.backend.fitta.controller.member.gym.staff;

import com.backend.fitta.dto.team.FindStaffByIdResponse;
import com.backend.fitta.dto.team.SaveStaffRequest;
import com.backend.fitta.dto.team.UpdateStaffRequest;
import com.backend.fitta.service.apiService.interfaces.StaffApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/staff")
public class StaffController {

    private final StaffApiService staffApiService;

    @PostMapping
    public ResponseEntity<Long> saveStaff(@Valid @RequestBody SaveStaffRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffApiService.save(request));
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<FindStaffByIdResponse> findStaff(@PathVariable long staffId) {
        return ResponseEntity.ok(staffApiService.findById(staffId));
    }

    @PutMapping("/{staffId}")
    public ResponseEntity<Long> updateTeam(@PathVariable Long staffId, @Valid @RequestBody UpdateStaffRequest request) {
        return ResponseEntity.ok(staffApiService.update(staffId, request));
    }


    @DeleteMapping("/{staffId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long staffId) {
        staffApiService.delete(staffId);
        return ResponseEntity.noContent().build();
    }

}



