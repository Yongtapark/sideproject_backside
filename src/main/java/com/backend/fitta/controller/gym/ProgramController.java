package com.backend.fitta.controller.gym;

import com.backend.fitta.dto.program.ProgramInfo;
import com.backend.fitta.dto.program.SignUpProgram;

import com.backend.fitta.service.apiService.ProgramApiServiceImpl;
import com.backend.fitta.service.apiService.interfaces.GymApiService;
import com.backend.fitta.service.apiService.interfaces.ProgramApiService;
import com.backend.fitta.service.interfaces.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/programs")
public class ProgramController {
    private final ProgramService programService;

    private final GymApiService gymApiService;
    private final ProgramApiService programApiService;

    @PostMapping
    @Operation(summary = "교육클래스 생성")
    public ResponseEntity<Void> createClasses(@RequestBody SignUpProgram signUpProgram){
        gymApiService.createClasses(signUpProgram);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{programId}")
    public ResponseEntity<Void> deleteClasses(@PathVariable Long programId){
        programService.delete(programId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping({"/{gymId}"})
    @Operation(summary = "체육관 프로그램 조회")
    public ResponseEntity<List<ProgramInfo>> findAllByGymId(@PathVariable Long gymId){
       return ResponseEntity.ok().body(programApiService.findAllByGymId(gymId));
    }




}
