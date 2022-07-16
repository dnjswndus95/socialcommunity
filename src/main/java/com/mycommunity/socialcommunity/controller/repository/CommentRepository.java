package com.mycommunity.socialcommunity.controller.repository;

import com.mycommunity.socialcommunity.domain.Comment;
import com.mycommunity.socialcommunity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
