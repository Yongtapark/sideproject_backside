package com.backend.fitta.config.oauth.dto;

import com.backend.fitta.entity.user.Role;
import com.backend.fitta.entity.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String gender;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String pictures, String gender) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = pictures;
        this.gender = gender;
    }
    /* of - OAuth2User 에서 반환하는 사용자 정보는 Map 이기 때문에 값 하나하나를 변환해야 한다.*/
    public static OAuthAttributes of(String registrationId, String userNameAttributeName,Map<String,Object> attributes){
        return ofGoogle(userNameAttributeName,attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .pictures((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    /*toEntity() - User 엔티티를 생성
    * OAuthAttributes 에서 엔티티를 생성하는 시점은 처음가입할 때 이다.
    * 가입 할 때의 기본 권한을 GUEST로 주기 위해서 role 빌더 값에는 Role.GUEST를 사용한다.
    * */
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .build();
    }


}
