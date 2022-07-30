/*
package com.mycommunity.socialcommunity.application.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler { // 로그인 인증 실패 시 에러메세지 출력
    // SecurityConfig에서 핸들러로 등록

    private final HttpSession httpSession;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String errorMessage;

        if(exception instanceof BadCredentialsException)
            errorMessage = "아이디 혹은 비밀번호가 일치하지 않습니다.";
        else if(exception instanceof UsernameNotFoundException)
            errorMessage = "존재하지 않는 계정입니다.";
        else if(exception instanceof InternalAuthenticationServiceException)
            errorMessage = "시스템 문제로 인해 인증을 허가할 수 없습니다.";
        else if(exception instanceof AuthenticationCredentialsNotFoundException)
            errorMessage = "인증 요청이 거절되었습니다.";
        else
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다.";

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        setDefaultFailureUrl("임시" + errorMessage); // 에러 발생시 URL

        super.onAuthenticationFailure(request, response, exception);
        httpSession.invalidate(); // 세션 삭제
    }
}
*/
