package com.backend.fitta.controller.gym.owner;

import com.backend.fitta.service.interfaces.OwnerApiService;
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


@Tag(name = "오너", description = "오너 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/Owner")
public class OwnerController {

    private final OwnerApiService ownerApiService;

    @Operation(summary = "오너 등록 메서드", description = "오너 등록 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveOwner(@Valid @RequestBody SignUpOwnerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerApiService.save(request));
    }
}
