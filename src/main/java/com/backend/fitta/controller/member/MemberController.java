package com.backend.fitta.controller.member;

import com.backend.fitta.config.jwt.JwtTokenProvider;
import com.backend.fitta.config.jwt.TokenInfo;
import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.member.BasicMemberInfo;
import com.backend.fitta.dto.member.MemberLoginRequestDto;
import com.backend.fitta.dto.member.SignUpRequest;
import com.backend.fitta.dto.member.UpdateMemberRequest;
import com.backend.fitta.exception.MemberNotFoundException;
import com.backend.fitta.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "회원", description = "회원 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    /*@GetMapping("/userdata")
    public ResponseEntity<BasicMemberInfo> getMemberInfo(@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        Object[] objects = userDetails.getAuthorities().toArray();
        log.info("objects={}",objects);
        BasicMemberInfo member = memberService.findByEmail(username);
        return ResponseEntity.ok(member);
    }*/

    @GetMapping("/userdata")
    public ResponseEntity<BasicMemberInfo> getMemberInfo(HttpServletRequest request){
        String accessToken = getAccessTokenFromCookies(request);
        if(accessToken!=null&&jwtTokenProvider.validateToken(accessToken)){
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            String username = authentication.getName();
            BasicMemberInfo member = memberService.findByEmail(username);
            return ResponseEntity.ok(member);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    private String getAccessTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("accessToken")){
                   return cookie.getValue();
                }
            }
        }
        return null;
    }


    @PostMapping("/test")
    public String test() {
        return "success";
    }


    @PostMapping("/login")
    public ResponseEntity<TokenInfo> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto,HttpServletResponse response){
        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(email, password);
        /*Authorization 으로 값을 보내면 새로고침 시 access token 이 사라진다. 대신 cookie 로 값을 전송한다. */
        ResponseCookie cookie = ResponseCookie.from("accessToken", tokenInfo.getAccessToken())
                .httpOnly(false)
                .secure(true)
                .path("/")
                //.maxAge(60L)
                .domain(".fitta-git-dev-yiminwook.vercel.app")
                .sameSite("none")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        //http에서 https와 cross origin 환경을 진행하면 setCookie 속성이 적용되지 않는다.
        return ResponseEntity.ok(tokenInfo);
    }

    @Operation(summary = "회원 등록 메서드", description = "회원 등록 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveMember(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(request));
    }


    @Operation(summary = "회원 조회 메서드", description = "회원 id로 회원을 조회 할 수 있습니다.")
    @GetMapping("/{memberId}")
    public ResponseEntity<BasicMemberInfo> findMember(@PathVariable Long memberId) {
        validateExistMember(memberId);
        return ResponseEntity.ok(memberService.findMember(memberId));
    }

    @Operation(summary = "전체 회원 조회 메서드", description = "전체 회원을 조회 할 수 있습니다.")
    @GetMapping
    public ResponseEntity<Result<List<BasicMemberInfo>>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @Operation(summary = "회원 정보 수정 메서드", description = "회원 id로 회원을 찾아 회원의 정보를 수정 할 수 있습니다.")
    @PutMapping("/{memberId}")
    public ResponseEntity<Long> updateMember(@PathVariable Long memberId, @Valid @RequestBody UpdateMemberRequest request) {
        validateExistMember(memberId);
        return ResponseEntity.ok(memberService.update(memberId, request));
    }

    @Operation(summary = "회원 삭제 메서드", description = "회원 id로 회원을 삭제할 수 있습니다.")
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        validateExistMember(memberId);
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{memberEmail}")
    public ResponseEntity<BasicMemberInfo> findByEmail(@PathVariable String memberEmail){
        return ResponseEntity.ok(memberService.findByEmail(memberEmail));
    }



    private void validateExistMember(Long memberId) {
        memberService.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
    }
    @Operation(summary = "회원 팀 등록", description = "회원 id로 회원을 찾아 팀을 추가해줍니다.")
    @PostMapping("team/{memberId}/{teamId}")
    public ResponseEntity<Void> saveTeamMember(@PathVariable long memberId, @PathVariable long teamId) {
        memberService.saveTeamMember(memberId,teamId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "회원 헬스장 등록", description = "회원 id로 회원을 찾아 헬스장을 추가해줍니다.")
    @PostMapping("gym/{memberId}/{gymId}")
    public ResponseEntity<Void> saveGymMember(@PathVariable long memberId, @PathVariable long gymId) {
        memberService.saveGymMember(memberId,gymId);
        return ResponseEntity.noContent().build();
    }
}
