package com.mycommunity.socialcommunity.controller;

import com.mycommunity.socialcommunity.application.dto.PostsDto;
import com.mycommunity.socialcommunity.application.dto.UserDto;
import com.mycommunity.socialcommunity.application.security.auth.LoginUser;
import com.mycommunity.socialcommunity.application.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PostsApiController { // Posts의 CRUD 연산 시행

    private final PostsService postsService;

    /**
     * @RequestBody, @ResponseBody : 비동기 통신 (새로고침 x인 경우 대부분)에서 본문(Body)에 데이터를 JSON 형식으로 주고받는다.
     */

    // 게시글 작성은 요청을 받는 것이고, 게시글을 작성하는 유저는 웹상에서 받아 그 정보를 바탕으로 그 유저의 게시글을 작성
    @PostMapping("/posts")
    public ResponseEntity save(@RequestBody PostsDto.Request dto, @LoginUser UserDto.Response user){
        return ResponseEntity.ok(postsService.save(dto, user.getNickname()));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity read(@PathVariable Long id){
        return ResponseEntity.ok(postsService.findById(id));
    }

    // Put Get 등은 멱등성을 가지지만 Post는 멱등성을 가지지않음
    // 따라서 update는 PutMapping 사용
    // 제목 a 라는 게시글을 b로 수정 -> 여러 번 해도 같은 결과 (단, 서버 상태코드 응답은 다를 수 있음)
    @PutMapping("/posts/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsDto.Request dto){
        postsService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }




}
