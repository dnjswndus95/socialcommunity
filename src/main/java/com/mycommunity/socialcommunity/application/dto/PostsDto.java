package com.mycommunity.socialcommunity.application.dto;

import com.mycommunity.socialcommunity.domain.Comment;
import com.mycommunity.socialcommunity.domain.Posts;
import com.mycommunity.socialcommunity.domain.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;


public class PostsDto {

    @Builder
    @Data
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
        private final List<Comment> commentList;


        public Response(Posts posts){
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.content = posts.getContent();
            this.view = posts.getView();
            this.user = posts.getUser();
            this.commentList = posts.getComments();
        }
    }
}
