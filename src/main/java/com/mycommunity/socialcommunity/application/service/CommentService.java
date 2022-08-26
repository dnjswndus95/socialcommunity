package com.mycommunity.socialcommunity.application.service;

import com.mycommunity.socialcommunity.application.dto.CommentDto;
import com.mycommunity.socialcommunity.domain.Comment;
import com.mycommunity.socialcommunity.domain.Posts;
import com.mycommunity.socialcommunity.domain.User;
import com.mycommunity.socialcommunity.repository.CommentRepository;
import com.mycommunity.socialcommunity.repository.PostsRepository;
import com.mycommunity.socialcommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long save(Long id, String nickname, CommentDto.Request dto){
        User user = userRepository.findByNickname(nickname);
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글은 존재하지 않습니다. " + id));

        dto.setUser(user);
        dto.setPosts(posts);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return comment.getId();
    }

    @Transactional(readOnly = true)
    public List<CommentDto.Response> readAll(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글은 존재하지 않습니다. " + id));

        List<Comment> comments = posts.getComments();

        return comments.stream().map(comment -> new CommentDto.Response()).collect(Collectors.toList());
    }

    @Transactional
    public void update(Long id, CommentDto.Request dto){
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글은 존재하지 않습니다. " + id));

        comment.update(dto.getContent());
    }

    @Transactional
    public void delete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow( () ->
                new IllegalArgumentException("해당 댓글은 존재하지 않습니다. " + id));
        commentRepository.delete(comment);
    }
}
