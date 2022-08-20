package com.mycommunity.socialcommunity.application.service;

import com.mycommunity.socialcommunity.application.dto.PostsDto;
import com.mycommunity.socialcommunity.repository.PostsRepository;
import com.mycommunity.socialcommunity.repository.UserRepository;
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

    @Transactional
    public PostsDto.Response findById(Long id){
        Posts post = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지않습니다."));

        return new PostsDto.Response(post);
    }

    @Transactional(readOnly = true)
    public Page<Posts> search(String keyword, Pageable pageable){
        Page<Posts> postsList = postsRepository.findByTitleIgnoreCaseContaining(keyword, pageable);
        return postsList;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        postsRepository.delete(posts);
    }

    @Transactional
    public int updateView(Long id){
        return postsRepository.updateView(id);
    }


}
