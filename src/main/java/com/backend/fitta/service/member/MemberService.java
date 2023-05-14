package com.backend.fitta.service.member;

import com.backend.fitta.dto.Member.SignUpRequest;
import com.backend.fitta.entity.Member;
import com.backend.fitta.repository.member.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    public Long signUp(@Valid SignUpRequest rq) {
        Optional<Member> findMember = memberRepository.findByEmail(rq.getEmail());
        if (!findMember.isEmpty()) { //중복 체크
            log.info("이미 존재하는 id가 있습니다.");
            throw new IllegalArgumentException();
        }
        Member member = new Member(rq.getEmail(), rq.getName(), 12, rq.getAddress(), rq.getGender()
                , null, null, null, null, rq.getPhoneNumber(), rq.getBirthday());

        memberRepository.save(member);
        return 1L;
    }
}
