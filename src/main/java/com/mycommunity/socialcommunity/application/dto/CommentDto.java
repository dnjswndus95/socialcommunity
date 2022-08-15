package com.mycommunity.socialcommunity.application.dto;


import com.mycommunity.socialcommunity.domain.Comment;
import com.mycommunity.socialcommunity.domain.Posts;
import com.mycommunity.socialcommunity.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

        private String createdDate;
        private String modifiedDate;

        public Response(Comment comment){
            this.id = comment.getId();
            this.content = comment.getContent();

            this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
            this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

            this.userId = comment.getUser().getId();
            this.postsId = comment.getPosts().getId();
        }
    }
}
