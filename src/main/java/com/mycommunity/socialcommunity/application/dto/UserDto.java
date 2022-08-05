package com.mycommunity.socialcommunity.application.dto;

import com.mycommunity.socialcommunity.domain.Role;
import com.mycommunity.socialcommunity.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

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
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        private Role role;

        public User toEntity () {
            return User.builder()
                    .id(id)
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .role(role.USER)
                    .build();
        }
    }

    @Getter  //네트워크를 통해 전송해야 하기 때문에 Serializable로 직렬화
    public static class Response implements Serializable {
        private final Long id;
        private final String username;
        private final String password;
        private final String nickname;
        private final String email;
        private final String modifiedDate;
        private final Role role;

        public Response(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.nickname = user.getNickname();
            this.email = user.getEmail();
            this.role = user.getRole();
            this.modifiedDate = user.getModifiedDate();
        }

    }
}
