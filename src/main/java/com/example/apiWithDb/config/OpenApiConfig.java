package com.example.apiWithDb.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name="Nikolai",
                        email = "nikolay.gonchar.2002@mail.ru"
                ),
                description = "OpenApi documentation for Diplom",
                title = "OpenApi specification - Nikolai"
        ),
        servers = {
                @Server(
                        description = "local ENV",
                        url = "http://localhost:3001"
                ),
                @Server(
                        description = "Remote ENV",
                        url = "https://apiwithdb-u82g.onrender.com"
                )
        }
)

@SecurityScheme(
        name = "bearerAuth",
        description = "this auth uses one token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
