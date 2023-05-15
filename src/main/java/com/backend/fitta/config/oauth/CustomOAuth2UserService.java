package com.backend.fitta.config.oauth;

import com.backend.fitta.config.oauth.dto.OAuthAttributes;
import com.backend.fitta.config.oauth.dto.SessionUser;
import com.backend.fitta.entity.user.User;
import com.backend.fitta.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        /*
        registrationId - 현재 로그인 진행중인 서비스를 구분하는 코드
        지금은 구글만 사용하는 불필요한 값이지만, 이후 네이버 로그인 연동 시에 네이버 로그인인지, 구글 로그인인지 구분하기 위함
        */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        /*
        userNameAttributeName - OAuth2 로그인 진행 시 키가 되는 필드값을 이야기. Primary Key 와 같은 의미
        구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 지원하지 않음. 구글의 기본 코드는 "sub"
        이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용됨
        */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        /*OAuthAttributes - OAuth2UserService 를 통해 가져온 OAuth2User 의 attribute 를 담을 클래스*/
        OAuthAttributes attributes = OAuthAttributes.of(registrationId,userNameAttributeName,oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        /*SessionUser - 세션에 사용자 정보를 저장하기위한 Dto 클래스*/
        httpSession.setAttribute("user",new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }
    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
