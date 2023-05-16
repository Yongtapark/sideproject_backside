package com.backend.fitta.service.member;

import com.backend.fitta.dto.Member.SignUpRequest;
import com.backend.fitta.dto.Member.UpdateMemberRequest;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(SignUpRequest rq) {
        Optional<Member> findMember = memberRepository.findByEmail(rq.getEmail());
        if (!findMember.isEmpty()) { //중복 체크
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        if (!rq.getPassword().equals(rq.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        Member member = new Member(rq.getEmail(), rq.getPassword(), rq.getName(), rq.getBirthday(), rq.getPhoneNumber(), rq.getAddress()
                , rq.getGender(), null, null, null, null, null, null);
        memberRepository.save(member);
        return member.getId();
    }


    public Long update(String memberEmail, UpdateMemberRequest rq) {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow();
        member.changeMemberInfo(rq.getEmail(), rq.getPassword(),rq.getName(), rq.getBirthday(), rq.getPhoneNumber(), rq.getAddress(), rq.getHeight(), rq.getWeight(), rq.getOccupation(), rq.getNote());
        return member.getId();
    }


    public Member findMember(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow();
        return member;
    }


    public void deleteMember(String memberEmail) {
        Optional<Member> findMember = memberRepository.findByEmail(memberEmail);
        if (findMember.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }
        memberRepository.deleteByEmail(memberEmail);
    }
}
