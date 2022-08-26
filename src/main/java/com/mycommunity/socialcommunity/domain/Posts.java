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

    @Column(nullable = false, columnDefinition = "TEXT") // TEXT 형식으로 데이터 추출
    private String content;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String writer;

    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments = new ArrayList<>();

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
