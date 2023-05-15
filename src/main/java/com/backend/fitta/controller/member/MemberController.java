package com.backend.fitta.controller.member;

import com.backend.fitta.dto.Member.SignUpRequest;
import com.backend.fitta.dto.Member.UpdateMemberRequest;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public Long saveMember(@Valid @RequestBody SignUpRequest request) {
        return memberService.save(request);
    }

    @PutMapping("/{memberEmail}")
    public Long updateMember(@PathVariable String memberEmail, @Valid @RequestBody UpdateMemberRequest request) {
        return memberService.update(memberEmail, request);
    }

    @GetMapping("/{memberEmail}")
    public Member findMember(@PathVariable String memberEmail) {
        return memberService.findMember(memberEmail);
    }

    @DeleteMapping("/{memberEmail}")
    public void deleteMember(@PathVariable String memberEmail) {
        memberService.deleteMember(memberEmail);
    }
}
