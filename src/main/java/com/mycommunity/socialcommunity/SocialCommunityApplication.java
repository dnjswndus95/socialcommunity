package com.mycommunity.socialcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) // 데이터베이스를 무엇으로 할지 아직 정하지 않았다면 이 어노테이션을 붙여준다.
public class SocialCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialCommunityApplication.class, args);
	}

}
