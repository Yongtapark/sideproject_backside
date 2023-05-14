package com.backend.fitta.controller.member;

import com.backend.fitta.dto.Member.SignUpRequest;
import com.backend.fitta.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("member/signup")
    public Long signUp(@RequestBody SignUpRequest request) {
        return memberService.signUp(request);
    }

}
