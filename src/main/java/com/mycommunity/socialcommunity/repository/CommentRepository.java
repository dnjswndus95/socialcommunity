package com.mycommunity.socialcommunity.repository;

import com.mycommunity.socialcommunity.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
