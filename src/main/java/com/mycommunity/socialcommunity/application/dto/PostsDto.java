package com.mycommunity.socialcommunity.application.dto;

import com.mycommunity.socialcommunity.domain.Comment;
import com.mycommunity.socialcommunity.domain.Posts;
import com.mycommunity.socialcommunity.domain.User;
import lombok.*;

import java.util.List;


public class PostsDto {

    @Getter
    @RequiredArgsConstructor
    public static class Response{

        /**
         * 게시글 정보를 응답하기만 하는 클래스이므로
         * Posts의 정보를 받아 final로 init해주고 수정방지.
         */
        private final Long id;
        private final String title;
        private final String content;
        private final int view;
        private final User user;
        private final String createdDate, modifiedDate;
        private final List<Comment> commentList;


        public Response(Posts posts){
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.content = posts.getContent();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
            this.view = posts.getView();
            this.user = posts.getUser();
            this.commentList = posts.getComments(); //수정필요
        }
    }

    @Getter
    @Data
    @AllArgsConstructor
    public static class Request{
        private Long id;
        private String title;
        private String content;
        private int view;
        private String createdDate, modifiedDate;
        private User user;
        // 생성할때 용도로 쓰기 때문에 List<Comment>는 받지않음.

        public Posts toEntity(){
            return Posts.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .view(0)
                    .user(user)
                    .build();
        }
    }
}
