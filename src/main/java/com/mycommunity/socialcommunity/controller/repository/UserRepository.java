package com.mycommunity.socialcommunity.controller.repository;

import com.mycommunity.socialcommunity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);
}
