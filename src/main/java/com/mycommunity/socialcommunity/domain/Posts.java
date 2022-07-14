package com.mycommunity.socialcommunity.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Posts extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTS_ID")
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(name = "POSTS_CONTENT", nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private int view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

}
