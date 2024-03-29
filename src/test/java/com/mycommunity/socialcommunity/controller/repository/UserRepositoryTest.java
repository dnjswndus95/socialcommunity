package com.mycommunity.socialcommunity.controller.repository;

import com.mycommunity.socialcommunity.domain.Role;
import com.mycommunity.socialcommunity.domain.User;
import com.mycommunity.socialcommunity.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @AfterEach
    public void clear(){
        userRepository.deleteAll();
    }

    @Test
    public void 회원생성(){
        String username = "tester";
        String password = "qwe123";
        String nickname = "tester_nickname";
        String encPassword = encoder.encode(password);

        userRepository.save(User.builder()
                .username(username)
                .password(encPassword)
                .nickname(nickname)
                .role(Role.USER)
                .email("xxx@naver.com")
                .build());

        User user = userRepository.findByNickname(nickname);

        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(encPassword);
    }

}