package com.backend.fitta.controller.member;

import com.backend.fitta.dto.Member.FindByEmailResponse;
import com.backend.fitta.dto.Member.SignUpRequest;
import com.backend.fitta.dto.Member.UpdateMemberRequest;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Long> saveMember(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(request));
    }

    @PutMapping("/{memberEmail}")
    public ResponseEntity<Long> updateMember(@PathVariable String memberEmail, @Valid @RequestBody UpdateMemberRequest request) {
        Optional<Member> findMember = memberService.findByEmail(memberEmail);
        if (findMember.isPresent()) {
            return ResponseEntity.ok(memberService.update(memberEmail, request));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{memberEmail}")
    public ResponseEntity<FindByEmailResponse> findMember(@PathVariable String memberEmail) {
        Optional<Member> findMember = memberService.findByEmail(memberEmail);
        if (findMember.isPresent()) {
            return ResponseEntity.ok(memberService.findMember(memberEmail));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{memberEmail}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberEmail) {
        Optional<Member> findMember = memberService.findByEmail(memberEmail);
        if (findMember.isPresent()) {
            memberService.deleteMember(memberEmail);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
