package com.backend.fitta.service.member;

import com.backend.fitta.entity.member.Member;
import com.backend.fitta.exception.MemberNotFoundException;
import com.backend.fitta.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findMemberByEmail(String email){
        return memberRepository.findByEmail(email).orElseThrow(()->new MemberNotFoundException());
    }

}
