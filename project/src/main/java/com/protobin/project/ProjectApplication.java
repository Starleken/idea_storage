package com.protobin.project;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ProjectApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}

}
