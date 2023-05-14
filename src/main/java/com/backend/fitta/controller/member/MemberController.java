package com.backend.fitta.controller.member;

import com.backend.fitta.dto.Member.SignUpRequest;
import com.backend.fitta.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    @PostMapping("member/signup")
    public Long signUp(@Valid @RequestBody SignUpRequest request) {
        return memberService.signUp(request);
    }
}
