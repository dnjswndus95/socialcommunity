package com.mycommunity.socialcommunity.application.dto;

import com.mycommunity.socialcommunity.domain.Comment;
import com.mycommunity.socialcommunity.domain.Posts;
import com.mycommunity.socialcommunity.domain.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


public class PostsDto {

    @Getter
    public static class Response{

        /**
         * 게시글 정보를 응답하기만 하는 클래스이므로
         * Posts의 정보를 받아 final로 init해주고 수정방지.
         */
        private final Long id;
        private final String title;
        private final String content;
        private final int view;
        private final Long userId;
        private final String createdDate, modifiedDate;
        private final List<CommentDto.Response> commentList;

        // writer 추가
        private final String writer;

        public Response(Posts posts){
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.content = posts.getContent();
            this.writer = posts.getWriter();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
            this.view = posts.getView();
            this.userId = posts.getUser().getId();
            this.commentList = posts.getComments().stream().map(comment -> new CommentDto.Response()).collect(Collectors.toList());
            //List에 CommentDto.Response를 받기위해 수정
        }
    }

    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        private Long id;
        private String title;
        private String content;
        private int view;
        private String createdDate, modifiedDate;
        private User user;
        private String writer;
        // 생성, 수정 할때 용도로 쓰기 때문에 List<Comment>는 받지않음.

        public Posts toEntity(){
            return Posts.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .view(0)
                    .user(user)
                    .writer(writer)
                    .build();
        }
    }
}
