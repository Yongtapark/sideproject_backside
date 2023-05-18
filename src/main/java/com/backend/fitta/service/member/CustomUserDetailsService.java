package com.backend.fitta.service.member;

import com.backend.fitta.entity.member.Member;
import com.backend.fitta.entity.user.Role;
import com.backend.fitta.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(()->new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member){
        return User.builder()
                .username(member.getEmail())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRoles().name())
                .build();

    }
}
