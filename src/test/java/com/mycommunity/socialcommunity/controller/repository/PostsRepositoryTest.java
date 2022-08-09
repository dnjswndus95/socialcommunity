package com.mycommunity.socialcommunity.controller.repository;

import com.mycommunity.socialcommunity.domain.Posts;
import com.mycommunity.socialcommunity.domain.Role;
import com.mycommunity.socialcommunity.domain.User;
import com.mycommunity.socialcommunity.repository.PostsRepository;
import com.mycommunity.socialcommunity.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    public void reset() {
        userRepository.deleteAll();
        postsRepository.deleteAll();
    }

    @AfterEach
    public void clear() {
        userRepository.deleteAll();
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_생성_가져오기() {

        //유저 생성
        String username = "tester";
        String password = "qwe123";
        String nickname = "tester_nickname";
        String encPassword = encoder.encode(password);

        User user = User.builder()
                .username(username)
                .password(encPassword)
                .nickname(nickname)
                .role(Role.USER)
                .email("xxx@naver.com")
                .build();
        userRepository.save(user);



        String title = "제목 입니다.";
        String content = "내용 입니다";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .view(0)
                .user(user)
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

        //log.info(posts);
    }
}