package com.backend.fitta.controller.staff;

import com.backend.fitta.dto.staff.BasicStaffInfo;
import com.backend.fitta.dto.staff.SaveStaffRequest;
import com.backend.fitta.dto.staff.UpdateStaffRequest;
import com.backend.fitta.repository.staff.StaffSearchCond;
import com.backend.fitta.service.apiService.interfaces.StaffApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Tag(name = "스태프", description = "스태프 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/staffs")
public class StaffController {

    private final StaffApiService staffApiService;

    @Operation(summary = "스태프 등록 메서드", description = "스태프 등록 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveStaff(@Valid @RequestBody SaveStaffRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffApiService.save(request));
    }

    @Operation(summary = "스태프 조회 메서드", description = "스태프 id로 스태프를 조회 할 수 있습니다.")
    @GetMapping("/{staffId}")
    public ResponseEntity<BasicStaffInfo> findStaff(@PathVariable long staffId) {
        return ResponseEntity.ok(staffApiService.findById(staffId));
    }

    @Operation(summary = "전체 스태프 조회 메서드", description = "전체 스태프를 조회 할 수 있습니다.")
    @GetMapping("/gym/{gymId}")
    public ResponseEntity<Page<BasicStaffInfo>> staffPaging(@PathVariable long gymId, String query, @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BasicStaffInfo> staffInfoPage = null;
        StaffSearchCond cond = new StaffSearchCond();
        cond.setGymId(gymId);
        if (query != null && !query.equals("")) {
            cond.setStaffName(query);
        }
        staffInfoPage = staffApiService.findSearch(cond, pageable);

        return ResponseEntity.ok(staffInfoPage);
    }

    @Operation(summary = "스태프 삭제 메서드", description = "스태프 id로 스태프를 삭제할 수 있습니다.")
    @DeleteMapping("/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long staffId) {
        staffApiService.delete(staffId);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "스태프 정보 수정 메서드", description = "스태프 id로 스태프를 찾아 스태프의 정보를 수정 할 수 있습니다.")
    @PutMapping("/{staffId}")
    public ResponseEntity<Long> updateStaff(@PathVariable Long staffId, @Valid @RequestPart UpdateStaffRequest request,
                                            @RequestPart(value = "profileImage", required = false) Optional<MultipartFile> profileImage) throws IOException {
        return ResponseEntity.ok(staffApiService.update(staffId, request,profileImage.orElse(null)));
    }

    @Operation(summary = "스태프 팀 등록", description = "스태프를 팀에 등록시키는 메서드입니다.")
    @PostMapping("team/{staffId}/{teamId}")
    public ResponseEntity<Void> saveTeamStaff(@PathVariable long staffId, @PathVariable long teamId) {
        staffApiService.saveTeamStaff(staffId, teamId);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "스태프 헬스장 등록", description = "스태프를 헬스장에 등록시키는 메서드입니다.")
    @PostMapping("gym/{staffId}/{gymId}")
    public ResponseEntity<Void> saveGymStaff(@PathVariable long staffId, @PathVariable long gymId) {
        staffApiService.saveGymStaff(staffId, gymId);
        return ResponseEntity.noContent().build();
    }


}



