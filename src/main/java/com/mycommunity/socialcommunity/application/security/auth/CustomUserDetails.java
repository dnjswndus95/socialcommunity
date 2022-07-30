package com.mycommunity.socialcommunity.application.security.auth;

import com.mycommunity.socialcommunity.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// Spring Security에서 사용자의 정보를 담는 클래스
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();

        //유저의 권한목록을 추가
        collector.add(() -> "ROLE_" + user.getRole());

        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }



/**
     * true : 만료안됨
     * false : 만료됨
     */

    @Override // 계정 만료 여부
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정 잠김
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 비밀번호 만료 여부
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 활성화 여부
    public boolean isEnabled() {
        return true;
    }
}

