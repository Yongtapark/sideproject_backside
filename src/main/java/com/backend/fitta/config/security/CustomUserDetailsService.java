package com.backend.fitta.config.security;

import com.backend.fitta.entity.utils.Users;
import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.member.MemberRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            return memberRepository.findByEmail(email)
                    .map(this::createUsersDetails)
                    //orElseGet 을 사용하면 trt-catch 처럼 다음 코드를 이어갈수 있다.
                    .orElseGet(
                            ()-> ownerRepository.findByEmail(email)
                            .map(this::createUsersDetails)
                            .orElseThrow(()->new  UsernameNotFoundException("해당 유저를 찾을 수 없습니다."))
                    );
    }

    private UserDetails createUsersDetails(Users users){
        return User.builder()
                .username(users.getEmail())
                .password(passwordEncoder.encode(users.getPassword()))
                .roles(users.getRole().name())
                .build();

    }


    // 언젠가 쓸날이 올까?
   /* public UserDetails loadUserByUsernameAndRole(String email, Role role) {
        log.info("loadUserByUsernameAndRole={}",email);
        if(role==Role.MEMBER){
            return memberRepository.findByEmail(email)
                    .map(this::createUsersDetails)
                    .orElseThrow(()->new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
        }else {
            return ownerRepository.findByEmail(email)
                    .map(this::createUsersDetails)
                    .orElseThrow(()->new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
        }
    }*/







}
