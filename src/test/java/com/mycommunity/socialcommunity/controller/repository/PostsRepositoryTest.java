package com.mycommunity.socialcommunity.controller.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostsRepository postsRepository;
}