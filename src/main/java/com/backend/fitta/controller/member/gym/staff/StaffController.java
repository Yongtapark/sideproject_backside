package com.backend.fitta.controller.member.gym.staff;

import com.backend.fitta.dto.team.FindStaffByIdResponse;
import com.backend.fitta.dto.team.SaveStaffRequest;
import com.backend.fitta.service.interfaces.StaffService;
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

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<Long> saveStaff(@Valid @RequestBody SaveStaffRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffService.save(request));
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<FindStaffByIdResponse> findStaff(@PathVariable long staffId) {
        return ResponseEntity.ok(staffService.findById(staffId));
    }


}



