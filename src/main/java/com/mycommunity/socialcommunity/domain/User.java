package com.mycommunity.socialcommunity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @AllArgsConstructor : 모든 멤버변수를 파라미터로 받는 생성자
 * @NoArgsConstructor : 파라미터가 없는 기본 생성자
 * @RequiredArgsConstructor : final 이나 @Notnull인 멤버변수만 파라미터로 받는 생성자
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false, length = 30, unique = true)
    private String nickname;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 40)
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public String getRoleValue(){
        return this.role.getValue();
    }

    public void update(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }

    public User updateModifiedDate(){
        this.onPreUpdate();
        return this;
    }


}
