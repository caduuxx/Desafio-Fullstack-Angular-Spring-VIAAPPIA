package com.desafioviaappia.api.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        final String scheme = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("Incidents API")
                        .version("v1")
                        .description("""
                                Autenticação: faça POST /auth/login com { "email", "password" }.
                                Use o token no header: Authorization: Bearer <token>.
                                Usuários seed:
                                  - admin@viaappia.com / 123456 (ADMIN)
                                  - reader@viaappia.com / 123456 (READ)
                                """))
                .components(new Components().addSecuritySchemes(scheme,
                        new SecurityScheme()
                                .name(scheme)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList(scheme));
    }
}
