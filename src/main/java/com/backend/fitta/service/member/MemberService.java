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
public class MemberService {

    private final MemberRepository memberRepository;
    @Transactional
    public Long save(SignUpRequest rq) {
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

    @Transactional
    public Long update(String memberEmail, UpdateMemberRequest rq) {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow();
        member.setName(rq.getName());
        member.setAge(rq.getAge());
        member.setAddress(rq.getAddress());
        member.setHeight(rq.getHeight());
        member.setWeight(rq.getWeight());
        member.setOccupation(rq.getOccupation());
        member.setPhoneNumber(rq.getPhoneNumber());
        member.setBirthday(rq.getBirthday());
        member.setNote(rq.getNote());

        return member.getId();
    }

    @Transactional
    public Member findMember(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow();
        return member;
    }

    @Transactional
    public void deleteMember(String memberEmail) {
        memberRepository.deleteByEmail(memberEmail);
    }
}
