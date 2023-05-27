package com.backend.fitta.controller.gym.owner;

import com.backend.fitta.dto.member.UpdateMemberRequest;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.dto.owner.UpdateOwnerRequest;
import com.backend.fitta.exception.MemberNotFoundException;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "오너", description = "오너 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerApiService ownerApiService;

    @Operation(summary = "오너 등록 메서드", description = "오너 등록 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveOwner(@Valid @RequestBody SignUpOwnerRequest request) {
        log.info("owner={}",request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerApiService.save(request));
    }

    @Operation(summary = "오너 정보 수정 메서드", description = "오너의 정보를 수정 할 수 있습니다.")
    @PutMapping("/{ownerId}")
    public ResponseEntity<Long> updateOwner(@PathVariable Long ownerId, @Valid @RequestBody UpdateOwnerRequest request) {
        return ResponseEntity.ok(ownerApiService.update(ownerId, request));
    }

}
