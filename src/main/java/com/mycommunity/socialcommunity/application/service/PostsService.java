package com.mycommunity.socialcommunity.application.service;

import com.mycommunity.socialcommunity.controller.repository.PostsRepository;
import com.mycommunity.socialcommunity.controller.repository.UserRepository;
import com.mycommunity.socialcommunity.domain.Posts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
}
