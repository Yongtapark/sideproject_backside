package com.backend.fitta.config.security;

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


        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()){
            log.info("isPresent={}",email);
            return member
                    .map(this::createMemberDetails)
                    .orElseThrow(()->new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
        }else {
            log.info("else={}",email);
            Optional<Owner> owner = ownerRepository.findByEmail(email);
                return owner
                        .map(this::createOwnerDetails)
                        .orElseThrow(()->new  UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

        }

    }

    public UserDetails loadUserByUsernameAndRole(String email, Role role) {
        log.info("loadUserByUsernameAndRole={}",email);
        if(role==Role.MEMBER){
            return memberRepository.findByEmail(email)
                    .map(this::createMemberDetails)
                    .orElseThrow(()->new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
        }else {
            return ownerRepository.findByEmail(email)
                    .map(this::createOwnerDetails)
                    .orElseThrow(()->new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
        }
    }



    private UserDetails createMemberDetails(Member member){
        return User.builder()
                .username(member.getEmail())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRole().name())
                .build();

    }

    private UserDetails createOwnerDetails(Owner owner){
        return User.builder()
                .username(owner.getEmail())
                .password(passwordEncoder.encode(owner.getPassword()))
                .roles(owner.getRole().name())
                .build();

    }


}
