package com.mycommunity.socialcommunity.application.security.oauth;

import com.mycommunity.socialcommunity.domain.Role;
import com.mycommunity.socialcommunity.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * OAuth DTO 클래스
 */

@Slf4j
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OAuthAttributes {

    private Map<String, Object> attributes; // OAuth2에서 JSON으로 반환하기 때문에 Map을 사용.
    private String nameAttributeKey;
    private String username;
    private String nickname;
    private String email;
    private Role role;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        log.info("naver : " + response);

        return OAuthAttributes.builder()
                .username((String) response.get("email"))
                .email((String) response.get("email"))
                .nickname((String) response.get("nickname"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .username((String) attributes.get("email"))
                .email((String) attributes.get("email"))
                .nickname((String) attributes.get("name"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .username(email)
                .email(email)
                .nickname(nickname)
                .role(Role.SOCIAL)
                .build();
    }
}
