package com.mycommunity.socialcommunity.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "COMMENT_CONTENT", nullable = false, length = 40)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;


}
