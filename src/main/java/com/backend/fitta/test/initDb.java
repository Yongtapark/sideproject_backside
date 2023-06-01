package com.backend.fitta.test;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.member.MemberRepository;
import com.backend.fitta.service.member.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
    }


@Component
@Transactional
@RequiredArgsConstructor
    static class InitService{
    private final MemberRepository memberRepository;



    public void dbInit(){
        Member member = Member
                .builder()
                .email("email@email.com")
                .password("password")
                .roles(Role.USER)
                .build();
        memberRepository.save(member);

    }


}
}
