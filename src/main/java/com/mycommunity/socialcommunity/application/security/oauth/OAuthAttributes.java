package com.mycommunity.socialcommunity.application.security.oauth;

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

    private Map<String, Object> attributes; // OAuth2에서 반환하는 사용자 정보는 Map이다.

    private String nameAttributeKey;
    private String username;
    private String nickname;
    private String email;

    private static OAuthAttributes of(String registrationId,
                                      String userNameAttributeName,
                                      Map<String, Object> attributes){
        if("naver".equals(registrationId))
            return ofNaver("id", attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName,
                                           Map<String, Object> attributes) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        log.info("naver : " +response);

        return OAuthAttributes.builder()
                .username((String) response.get("email"))
                .email((String) response.get("email"))
                .nickname((String) response.get("nickname"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes){

    }


}
