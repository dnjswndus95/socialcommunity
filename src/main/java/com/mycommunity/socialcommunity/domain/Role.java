package com.mycommunity.socialcommunity.domain;

import lombok.Getter;

@Getter
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    COMMENT("ROLE_COMMENT");
}
