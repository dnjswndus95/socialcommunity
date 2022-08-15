package com.mycommunity.socialcommunity.controller;

import com.mycommunity.socialcommunity.application.dto.UserDto;
import com.mycommunity.socialcommunity.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    /**
     * ResponseEntity
     * HttpEntity 를 상속받아 ResponseEntity, RequestEntity로 사용할 수 있다.
     * HttpHeader, HttpBody를 포함한 클래스
     * 응답과 요청에 HttpStatus와 HttpBody로 message 전달 가능
     *
     **/
    @PutMapping("/api/user")
    public ResponseEntity<String> modify(@RequestBody UserDto.Request dto){
        userService.modify(dto);

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
