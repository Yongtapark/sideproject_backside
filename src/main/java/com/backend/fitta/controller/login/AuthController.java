package com.backend.fitta.controller.login;

import com.backend.fitta.config.security.jwt.JwtTokenManager;
import com.backend.fitta.dto.auth.TokenRefreshRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenManager jwtTokenManager;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request){
        String refreshToken = request.getRefreshToken();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String newAccessToken = jwtTokenManager.refreshAccessToken(refreshToken, authentication);

        return ResponseEntity.ok(newAccessToken);
    }

}
