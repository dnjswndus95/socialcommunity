package com.mycommunity.socialcommunity.controller;

import com.mycommunity.socialcommunity.application.dto.CommentDto;
import com.mycommunity.socialcommunity.application.dto.UserDto;
import com.mycommunity.socialcommunity.application.security.auth.LoginUser;
import com.mycommunity.socialcommunity.application.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity save(@PathVariable Long id, @RequestBody CommentDto.Request dto,
                               @LoginUser UserDto.Response user){
        return ResponseEntity.ok((commentService.save(id, user.getNickname(), dto)));
    }

    @GetMapping("/posts/{id}/comments")
    public List<CommentDto.Response> read(@PathVariable Long id){
        return commentService.readAll(id);
    }

    @PutMapping("/posts/{id}/comments/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody CommentDto.Request dto){
        commentService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/posts/{id}/comments/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        commentService.delete(id);
        return ResponseEntity.ok(id);
    }
}
