package com.mycommunity.socialcommunity.controller;

import com.mycommunity.socialcommunity.application.dto.CommentDto;
import com.mycommunity.socialcommunity.application.dto.PostsDto;
import com.mycommunity.socialcommunity.application.dto.UserDto;
import com.mycommunity.socialcommunity.application.security.auth.LoginUser;
import com.mycommunity.socialcommunity.application.service.PostsService;
import com.mycommunity.socialcommunity.domain.Posts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostsIndexController { // 게시글 화면 컨트롤러

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model,
                        @PageableDefault(sort = "id", direction = Sort.Direction.DESC)Pageable pageable,
                        @LoginUser UserDto.Response user){
        Page<Posts> list = postsService.getPageList(pageable);

        if(user != null)
            model.addAttribute("user", user);

        model.addAttribute("posts", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrevious", list.hasPrevious());

        return "index";
    }

    @GetMapping("/posts/write")
    public String write(Model model, @LoginUser UserDto.Response user){
        if(user != null)
            model.addAttribute("user", user);

        return "posts/posts-write";
    }

    @GetMapping("/posts/read/{id}")
    public String read( Model model, @PathVariable Long id, @LoginUser UserDto.Response user){
        PostsDto.Response post = postsService.findById(id);
        List<CommentDto.Response> comments = post.getCommentList();

        // 여기까지
        if()
    }
}
