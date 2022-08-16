package com.mycommunity.socialcommunity.repository;

import com.mycommunity.socialcommunity.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // IgnoreCase로 영어 대소문자 구분없이 쿼리가 나가게
    Page<Posts> findByTitleIgnoreCaseContaining(String keyword, Pageable pageable);
}
