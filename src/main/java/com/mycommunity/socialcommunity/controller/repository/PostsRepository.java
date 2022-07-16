package com.mycommunity.socialcommunity.controller.repository;

import com.mycommunity.socialcommunity.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    Page<Posts> findByTitle(String keyword, Pageable pageable);
}
