package com.backend.fitta.service.member;

import com.backend.fitta.dto.Member.SignUpRequest;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    public Long signUp(SignUpRequest rq) {
        Optional<Member> findMember = memberRepository.findByEmail(rq.getEmail());
        if (!findMember.isEmpty()) { //중복 체크
            throw new IllegalArgumentException();
        }
        if (!rq.getPassword().equals(rq.getPasswordConfirm())) {
            throw new IllegalArgumentException();
        }
        Member member = new Member(rq.getEmail(), rq.getName(), null, rq.getAddress(), rq.getGender()
                , null, null, null, null, rq.getPhoneNumber(), rq.getBirthday(), null);
        memberRepository.save(member);
        return member.getId();
    }
}
