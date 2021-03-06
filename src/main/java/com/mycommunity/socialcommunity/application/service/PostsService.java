package com.mycommunity.socialcommunity.application.service;

import com.mycommunity.socialcommunity.application.dto.PostsDto;
import com.mycommunity.socialcommunity.controller.repository.PostsRepository;
import com.mycommunity.socialcommunity.controller.repository.UserRepository;
import com.mycommunity.socialcommunity.domain.Posts;
import com.mycommunity.socialcommunity.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    // 게시글 등록
    @Transactional
    public Long save(PostsDto.Request dto, String nickname){
        User user = userRepository.findByNickname(nickname);
        dto.setUser(user);
        Posts posts = dto.toEntity();
        postsRepository.save(posts);
        log.info("PostsService.save()");

        return posts.getId();
    }

    // 게시글 수정
    @Transactional
    public void update(Long id, PostsDto.Request dto){
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        posts.update(dto.getTitle(), dto.getContent());
    }

    // 게시글 페이지로 보기
    @Transactional(readOnly = true)
    public Page<Posts> getPageList(Pageable pageable){
        return postsRepository.findAll(pageable);
    }




}
