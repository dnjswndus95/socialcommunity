package com.mycommunity.socialcommunity.application.security.auth;

import com.mycommunity.socialcommunity.application.dto.UserDto;
import com.mycommunity.socialcommunity.controller.repository.UserRepository;
import com.mycommunity.socialcommunity.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService { // 유저의 정보를 가져오는 인터페이스

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException( username + "의 이름의 유저는 존재하지 않습니다."));

        //세션에 유저 저장
        httpSession.setAttribute("user", new UserDto.Response(user));

        return new CustomUserDetails(user);
    }
}
