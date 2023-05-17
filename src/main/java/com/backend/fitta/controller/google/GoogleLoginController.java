package com.backend.fitta.controller.google;

import com.backend.fitta.config.oauth.CustomOAuth2UserService;
import com.backend.fitta.config.oauth.dto.OAuthAttributes;
import com.backend.fitta.config.oauth.dto.SessionUser;
import com.backend.fitta.entity.user.User;
import com.backend.fitta.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GoogleLoginController {
    private final HttpSession httpSession;
    private final UserRepository userRepository;

   @GetMapping("/api/user/login")
    public SessionUser login(@RequestBody OAuthAttributes attributes){
       User user = saveOrUpdate(attributes);
       httpSession.setAttribute("user",new SessionUser(user));
       return new SessionUser(user);
   }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
