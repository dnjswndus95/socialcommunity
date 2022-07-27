package com.mycommunity.socialcommunity.application.security.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Session 중복 코드 제거를 위해 @LoginUser 어노테이션 생성
 * 중복코드 : SessionUser user = (SessionUser)httpSession.getAttribute("user");
 */

@Target(ElementType.PARAMETER) // 파라미터로 선언된 객체만 사용한다.
@Retention(RetentionPolicy.RUNTIME) // Runtime까지 남아있는다.
public @interface LoginUser {

}
