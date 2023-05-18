package com.backend.fitta.service.member;

import com.backend.fitta.config.jwt.JwtTokenProvider;
import com.backend.fitta.config.jwt.TokenInfo;
import com.backend.fitta.dto.member.FindByEmailResponse;
import com.backend.fitta.dto.member.SignUpRequest;
import com.backend.fitta.dto.member.UpdateMemberRequest;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.exception.AlreadyExistMemberException;
import com.backend.fitta.exception.PWNotCorrespondException;
import com.backend.fitta.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenInfo login(String email,String password){
        //1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이 때, authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken(email,password);

        //2. 실제 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        return tokenInfo;
    }

    public Long save(SignUpRequest rq) {
        Optional<Member> findMember = memberRepository.findByEmail(rq.getEmail());
        if (!findMember.isEmpty()) { //중복 체크
            throw new AlreadyExistMemberException();
        }
        if (!rq.getPassword().equals(rq.getPasswordConfirm())) {
            throw new PWNotCorrespondException();
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


    public FindByEmailResponse findMember(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow();
        return new FindByEmailResponse(member.getId(),member.getEmail(),member.getPassword(),member.getName(),member.getBirthday(),member.getPhoneNumber(),member.getAddress(),member.getGender()
        ,member.getHeight(),member.getWeight(),member.getOccupation(),member.getNote(),member.getTeam(),member.getGym());
    }


    public void deleteMember(String memberEmail) {
        memberRepository.deleteByEmail(memberEmail);
    }

    public Optional<Member> findByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail);
    }
}
