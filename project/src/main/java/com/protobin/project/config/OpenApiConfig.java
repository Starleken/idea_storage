package com.protobin.project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "Protobin", version = "1.0.0.", description = """
				### Project service
				Это модуль для работы с проектами, здесь вы можете получить:
				- теги
				- стек
				- прокты
				- участников проекта
				- права участников
				и т.д.
				""",
                license = @License(name = "Proprietary"),
                termsOfService = "https://protobin.com",
                contact = @Contact(name = "API Support", url = "https://protobin.com", email = "vogistv@gmail.com")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Локальная среда"),
                @Server(url = "https://api.protobin.com", description = "Продакшн")
        }
)
@SecurityScheme(
        name = "Bearer Авторизация",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT Authorization header using the Bearer scheme"
)
public class OpenApiConfig {
}
