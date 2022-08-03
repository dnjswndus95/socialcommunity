package com.mycommunity.socialcommunity.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;


class SecurityConfigTest {

    @Test
    public void 해쉬암호화(){
        String encodedPassword = new BCryptPasswordEncoder().encode("qwe123");
        System.out.println("encodedPassword = " + encodedPassword);
    }

}