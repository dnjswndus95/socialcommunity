package com.mycommunity.socialcommunity.application.validator;

import com.mycommunity.socialcommunity.application.dto.UserDto;
import com.mycommunity.socialcommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CustomValidators {

    @RequiredArgsConstructor
    @Component
    public static class EmailValidator extends AbstractValidator<UserDto.Request> {
        private final UserRepository userRepository;

        //Errors : 유효성 검증 결과를 저장할 때 사용함.
        @Override
        protected void doValidate(UserDto.Request dto, Errors errors) {
            if(userRepository.existsByEmail(dto.toEntity().getEmail())){
                errors.rejectValue("email", "중복된 이메일", "이미 사용중인 이메일입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class NicknameValidator extends AbstractValidator<UserDto.Request>{
        private final UserRepository userRepository;

        @Override
        protected void doValidate(UserDto.Request dto, Errors errors) {
            if(userRepository.existsByNickname(dto.toEntity().getNickname())){
                errors.rejectValue("nickname", "중복된 닉네임", "이미 사용중인 이메일입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class UsernameValidator extends AbstractValidator<UserDto.Request>{
        private final UserRepository userRepository;

        @Override
        protected void doValidate(UserDto.Request dto, Errors errors){
            if(userRepository.existsByUsername(dto.toEntity().getUsername())){
                errors.rejectValue("username", "중복된 아이디", "이미 사용중인 아이디입니다.");
            }
        }
    }
}
