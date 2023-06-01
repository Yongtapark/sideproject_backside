package com.backend.fitta.controller.owner;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.dto.owner.UpdateOwnerRequest;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerApiService.save(request));
    }

    @Operation(summary = "오너 조회 메서드", description = "오너 id로 오너를 조회 할 수 있습니다.")
    @GetMapping("/{ownerId}")
    public ResponseEntity<BasicOwnerInfo> findMember(@PathVariable Long ownerId) {
        return ResponseEntity.ok(ownerApiService.findById(ownerId));
    }

    @Operation(summary = "전체 오너 조회 메서드", description = "전체 오너를 조회 할 수 있습니다.")
    @GetMapping
    public ResponseEntity<Result<List<BasicOwnerInfo>>> findAll() {
        return ResponseEntity.ok(ownerApiService.findAll());
    }

    @Operation(summary = "오너 정보 수정 메서드", description = "오너의 정보를 수정 할 수 있습니다.")
    @PutMapping("/{ownerId}")
    public ResponseEntity<Long> updateOwner(@PathVariable Long ownerId, @Valid @RequestBody UpdateOwnerRequest request) {
        return ResponseEntity.ok(ownerApiService.update(ownerId, request));
    }

    @Operation(summary = "오너 삭제 메서드", description = "오너를 삭제할 수 있습니다.")
    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long ownerId) {
        ownerApiService.deleteOwner(ownerId);
        return ResponseEntity.noContent().build();
    }

}
