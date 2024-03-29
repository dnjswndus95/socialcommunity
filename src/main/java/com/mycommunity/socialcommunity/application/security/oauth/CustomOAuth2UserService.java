package com.mycommunity.socialcommunity.application.security.oauth;

import com.mycommunity.socialcommunity.application.dto.UserDto;
import com.mycommunity.socialcommunity.repository.UserRepository;
import com.mycommunity.socialcommunity.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // Google, Naver, Kakao id로 구분.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("registrationId : " + registrationId);

        String userNameAttributes = userRequest.getClientRegistration()
                                            .getProviderDetails()
                                            .getUserInfoEndpoint()
                                            .getUserNameAttributeName();

        log.info("=============================================");
        log.info("userNameAttributeName : " + userNameAttributes);

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributes, oAuth2User.getAttributes());
        log.info("=============================================");
        log.info("getAttributes(): " + attributes.getAttributes());

        User user = saverOrUpdate(attributes);

        httpSession.setAttribute("user", new UserDto.Response(user));

        return new DefaultOAuth2User(
                        Collections.singleton(new SimpleGrantedAuthority((user.getRoleValue()))),
                        attributes.getAttributes(),
                        attributes.getNameAttributeKey());
    }

    private User saverOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(User::updateModifiedDate)
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
