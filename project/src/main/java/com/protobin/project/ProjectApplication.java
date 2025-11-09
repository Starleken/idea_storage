package com.protobin.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@Slf4j
public class ProjectApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ProjectApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
		log.info("Documentation OpenApi UI: /swagger-ui/index.html");
		log.info("Documentation OpenApi Docs: /docs");
	}

}
