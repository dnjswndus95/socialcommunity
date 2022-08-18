package com.mycommunity.socialcommunity.controller;

import com.mycommunity.socialcommunity.application.dto.UserDto;
import com.mycommunity.socialcommunity.application.security.auth.LoginUser;
import com.mycommunity.socialcommunity.application.service.UserService;
import com.mycommunity.socialcommunity.application.validator.CustomValidators;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    private final CustomValidators.UsernameValidator usernameValidator;
    private final CustomValidators.EmailValidator emailValidator;
    private final CustomValidators.NicknameValidator nicknameValidator;


    /**
     * @InitBinder
     * Controller에 오는 요청에 대해 추가적인 설정을 할 때 사용
     * 위에서 만들어놓은 Validator로 유효성검증을 하기 위해 사용
     */
    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(usernameValidator);
        binder.addValidators(emailValidator);
        binder.addValidators(nicknameValidator);
    }

    @GetMapping("/auth/join")
    public String join(){
        return "/user/user-join";
    }

    @PostMapping("/auth/joinProc")
    public String joinProc(@Valid UserDto.Request dto, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("userDto", dto); // 회원가입을 실패하더라도 입력 데이터는 그대로 보존

            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
                // 어떤 필드때문에 실패했는지 찾아서 model에 저장
            }

            return "/user/user-join"; // 회원가입 페이지로 다시 돌아간다
        }

        // 실패하지 않았다면
        userService.joinUser(dto);
        return "redirect:/auth/login"; //로그인 페이지로 리다이렉트
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false)String error,   // 에러와 예외가 없을때 Exception 오류 안나게 하기 위해 false 처리
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/user/user-login";
    }

    @GetMapping("/logout")  // Post가 아닌 Get을 하는 이유는 CSRF 방지 | CSRF 토큰을 사용해도 되지만 CSRF 토큰을 사용하지 않을 경우 GetMapping으로 로그아웃 처리
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/modify")
    public String modify(@LoginUser UserDto.Response user, Model model){
        // user가 없다면 등록
        if(user != null){
            model.addAttribute("user", user);
        }
        return "/user/user-modify";
    }
}
