package com.example.product.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * Swagger Configuration
 * for customizing the swagger ui
 * */
@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = SwaggerConfig.SECURITY_SCHEME_BEARER_TOKEN_AUTH,
        description = "Please enter JWT token",
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {

    @Value("${server.servlet.context-path}")
    private String contextPath;
    public static final String SECURITY_SCHEME_BEARER_TOKEN_AUTH = "BearerTokenAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url(contextPath))
                .info(new Info()
                    .title("Product Service API")
                    .version("1.0")
                    .description("API documentation for Product Service")
                );
    }
}
