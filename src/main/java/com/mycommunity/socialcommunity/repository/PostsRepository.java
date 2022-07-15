package com.mycommunity.socialcommunity.repository;

import com.mycommunity.socialcommunity.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
