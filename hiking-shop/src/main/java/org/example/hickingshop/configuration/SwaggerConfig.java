package org.example.hickingshop.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hicking Shop API")
                        .version("1.0")
                        .description("Hicking Shop API"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme().name("bearerAuth")
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .type(SecurityScheme.Type.HTTP)));
    }

}

