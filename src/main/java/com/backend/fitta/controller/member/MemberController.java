package com.backend.fitta.controller.member;

import com.backend.fitta.config.jwt.TokenInfo;
import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.member.BasicMemberInfo;
import com.backend.fitta.dto.member.MemberLoginRequestDto;
import com.backend.fitta.dto.member.SignUpRequest;
import com.backend.fitta.dto.member.UpdateMemberRequest;
import com.backend.fitta.exception.MemberNotFoundException;
import com.backend.fitta.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "회원", description = "회원 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    @Tag(name = "회원정보 반환 api",description = "클라이언트에서 보내주는 accessToken 을 이용하여 회원 정보를 반환합니다.")
    @GetMapping("/userdata")
    public ResponseEntity<BasicMemberInfo> getMemberInfo(@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        Object[] objects = userDetails.getAuthorities().toArray();
        log.info("objects={}",objects);
        BasicMemberInfo member = memberService.findByEmail(username);

        return ResponseEntity.ok(member);
    }



    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @Tag(name = "로그인 api")
    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto){
        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(email, password);
        return tokenInfo;
    }

    @Operation(summary = "회원 등록 메서드", description = "회원 등록 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveMember(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(request));
    }


    @Operation(summary = "회원 조회 메서드", description = "회원 id로 회원을 조회 할 수 있습니다.")
    @GetMapping("/{memberId}")
    public ResponseEntity<BasicMemberInfo> findMember(@PathVariable Long memberId) {
        validateExistMember(memberId);
        return ResponseEntity.ok(memberService.findMember(memberId));
    }

    @Operation(summary = "전체 회원 조회 메서드", description = "전체 회원을 조회 할 수 있습니다.")
    @GetMapping
    public ResponseEntity<Result<List<BasicMemberInfo>>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @Operation(summary = "회원 정보 수정 메서드", description = "회원 id로 회원을 찾아 회원의 정보를 수정 할 수 있습니다.")
    @PutMapping("/{memberId}")
    public ResponseEntity<Long> updateMember(@PathVariable Long memberId, @Valid @RequestBody UpdateMemberRequest request) {
        validateExistMember(memberId);
        return ResponseEntity.ok(memberService.update(memberId, request));
    }

    @Operation(summary = "회원 삭제 메서드", description = "회원 id로 회원을 삭제할 수 있습니다.")
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        validateExistMember(memberId);
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{memberEmail}")
    public ResponseEntity<BasicMemberInfo> findByEmail(@PathVariable String memberEmail){
        return ResponseEntity.ok(memberService.findByEmail(memberEmail));
    }



    private void validateExistMember(Long memberId) {
        memberService.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
    }
    @Operation(summary = "회원 팀 등록", description = "회원 id로 회원을 찾아 팀을 추가해줍니다.")
    @PostMapping("team/{memberId}/{teamId}")
    public ResponseEntity<Void> saveTeamMember(@PathVariable long memberId, @PathVariable long teamId) {
        memberService.saveTeamMember(memberId,teamId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "회원 헬스장 등록", description = "회원 id로 회원을 찾아 헬스장을 추가해줍니다.")
    @PostMapping("gym/{memberId}/{gymId}")
    public ResponseEntity<Void> saveGymMember(@PathVariable long memberId, @PathVariable long gymId) {
        memberService.saveGymMember(memberId,gymId);
        return ResponseEntity.noContent().build();
    }
}
