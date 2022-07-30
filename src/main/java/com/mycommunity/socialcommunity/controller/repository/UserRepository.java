package com.mycommunity.socialcommunity.controller.repository;

import com.mycommunity.socialcommunity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);
    Optional<User> findByUsername(String username);

    //소셜 로그인
    Optional<User> findByEmail(String email);
}
