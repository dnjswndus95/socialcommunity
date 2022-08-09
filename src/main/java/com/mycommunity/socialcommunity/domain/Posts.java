package com.mycommunity.socialcommunity.domain;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Posts extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private int view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_posts_user_id")
    private User user;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
