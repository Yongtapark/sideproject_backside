package com.backend.fitta.controller.member;

import com.backend.fitta.config.jwt.TokenInfo;
import com.backend.fitta.dto.member.FindByEmailResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원", description = "회원 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }


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


    @Operation(summary = "회원 조회 메서드", description = "회원 email로 회원을 조회 할 수 있습니다.")
    @GetMapping("/{memberEmail}")
    public ResponseEntity<FindByEmailResponse> findMember(@PathVariable String memberEmail) {
        validateExistMember(memberEmail);
        return ResponseEntity.ok(memberService.findMember(memberEmail));
    }

    @Operation(summary = "회원 정보 수정 메서드", description = "회원 email로 회원을 찾아 회원의 정보를 수정 할 수 있습니다.")
    @PutMapping("/{memberEmail}")
    public ResponseEntity<Long> updateMember(@PathVariable String memberEmail, @Valid @RequestBody UpdateMemberRequest request) {
        validateExistMember(memberEmail);
        return ResponseEntity.ok(memberService.update(memberEmail, request));
    }

    @Operation(summary = "회원 삭제 메서드", description = "회원 email로 회원을 삭제할 수 있습니다.")
    @DeleteMapping("/{memberEmail}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberEmail) {
        validateExistMember(memberEmail);
        memberService.deleteMember(memberEmail);
        return ResponseEntity.noContent().build();
    }


    private void validateExistMember(String memberEmail) {
        memberService.findByEmail(memberEmail).orElseThrow(() -> new MemberNotFoundException());
    }
}
