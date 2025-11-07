package com.protobin.project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
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
