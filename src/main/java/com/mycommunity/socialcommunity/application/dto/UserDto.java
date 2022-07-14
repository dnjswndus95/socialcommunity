package com.mycommunity.socialcommunity.application.dto;

import com.mycommunity.socialcommunity.domain.User;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.*;

public class UserDto {

    /**
     * 애플리케이션의 기능이 늘어남에 따라 그 상황에 맞는 Dto 개수가 계속해서 늘어나므로
     * Dto를 InnerClass로 관리하여 편의성을 향상시킨다.
     */

    @Data
    @Builder
    public static class Request {
        private Long id;

        @NotBlank(message = "아이디는 필수입력사항입니다.")
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{6,20}$", message = "아이디는 특수문자를 제외하고 6~20 사이여야 합니다.")
        private String username;

        @NotBlank(message = "비밀번호는 필수입력사항입니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,18}", message = "비밀번호는 대문자, 소문자, 숫자, 특수문자를 한개 이상 포함하고 8~18 사이여야 합니다.")
        private String password;

        @NotBlank(message = "닉네임은 필수입력사항입니다.")
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{4,14}$", message = "닉네임은 특수문자를 제외하고 4~14 자리 사이여야 합니다.")
        private String nickname;

        @NotBlank(message = "이메일은 필수입력사항입니다.")
        @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        public User toEntity () {
            return User.builder()
                    .id(id)
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .build();
        }
    }

    public static class Response{
        /**
         * 이후에 정의..
         */
    }
}
