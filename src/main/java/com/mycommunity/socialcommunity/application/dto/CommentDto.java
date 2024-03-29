package com.mycommunity.socialcommunity.application.dto;


import com.mycommunity.socialcommunity.domain.Comment;
import com.mycommunity.socialcommunity.domain.Posts;
import com.mycommunity.socialcommunity.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentDto {

    @Getter
    @RequiredArgsConstructor
    public static class Response{
        private Long id;
        private String content;
        private Long userId;
        private Long postsId;
        private String nickname;

        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

        public Response(Comment comment){
            this.id = comment.getId();
            this.content = comment.getContent();

            this.createdDate = comment.getCreatedDate();
            this.modifiedDate = comment.getModifiedDate();

            this.nickname = comment.getUser().getNickname();
            this.userId = comment.getUser().getId();
            this.postsId = comment.getPosts().getId();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String content;

        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

        private User user;
        private Posts posts;

        public Comment toEntity(){
            return Comment.builder()
                    .id(id)
                    .content(content)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .user(user)
                    .posts(posts)
                    .build();
        }
    }
}
