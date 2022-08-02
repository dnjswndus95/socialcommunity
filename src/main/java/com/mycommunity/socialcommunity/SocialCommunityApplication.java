package com.mycommunity.socialcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) // 데이터베이스를 무엇으로 할지 아직 정하지 않았다면 이 어노테이션을 붙여준다.
public class SocialCommunityApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SocialCommunityApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SocialCommunityApplication.class);
	}
}
