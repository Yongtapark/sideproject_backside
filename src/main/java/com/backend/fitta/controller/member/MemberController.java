package com.backend.fitta.controller.member;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.member.*;
import com.backend.fitta.exception.MemberNotFoundException;
import com.backend.fitta.service.apiService.MemberApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Tag(name = "회원", description = "회원 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberApiService memberApiService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @Operation(summary = "회원 등록 메서드", description = "회원 등록 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveMember(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberApiService.save(request));
    }

    @Operation(summary = "회원 조회 메서드", description = "회원 id로 회원을 조회 할 수 있습니다.")
    @GetMapping("/{memberId}")
    public ResponseEntity<BasicMemberInfo> findMember(@PathVariable Long memberId) {
        validateExistMember(memberId);
        return ResponseEntity.ok(memberApiService.findMember(memberId));
    }

    @Operation(summary = "전체 회원 조회 메서드", description = "전체 회원을 조회 할 수 있습니다.")
    @GetMapping
    public ResponseEntity<Result<List<BasicMemberInfo>>> findAll() {
        return ResponseEntity.ok(memberApiService.findAll());
    }

    @Operation(summary = "회원 정보 수정 메서드", description = "회원 id로 회원을 찾아 회원의 정보를 수정 할 수 있습니다.")
    @PutMapping(value = "/{memberId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> updateMember(@PathVariable Long memberId, @Valid @RequestPart UpdateMemberRequest request,
                                             @RequestPart(value = "multipartFile", required = false) MultipartFile multipartFile) throws IOException {

        validateExistMember(memberId);
        return ResponseEntity.ok(memberApiService.update(memberId, request, multipartFile));
    }

    @Operation(summary = "회원 삭제 메서드", description = "회원 id로 회원을 삭제할 수 있습니다.")
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        validateExistMember(memberId);
        memberApiService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{memberEmail}")
    public ResponseEntity<BasicMemberInfo> findByEmail(@PathVariable String memberEmail){
        return ResponseEntity.ok(memberApiService.findByEmail(memberEmail));
    }



    private void validateExistMember(Long memberId) {
        memberApiService.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
    }
    @Operation(summary = "회원 팀 등록", description = "회원 id로 회원을 찾아 팀을 추가해줍니다.")
    @PostMapping("team/{memberId}/{teamId}")
    public ResponseEntity<Void> saveTeamMember(@PathVariable long memberId, @PathVariable long teamId) {
        memberApiService.saveTeamMember(memberId,teamId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "회원 헬스장 등록", description = "회원 id로 회원을 찾아 헬스장을 추가해줍니다.")
    @PostMapping("gym/{memberId}/{gymId}")
    public ResponseEntity<Void> saveGymMember(@PathVariable long memberId, @PathVariable long gymId) {
        memberApiService.saveGymMember(memberId,gymId);
        return ResponseEntity.noContent().build();
    }
}
