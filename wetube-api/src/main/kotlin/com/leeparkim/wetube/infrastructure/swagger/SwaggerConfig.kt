package com.leeparkim.wetube.infrastructure.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun customOpenAPI(@Value("\${springdoc.version}") springdocVersion: String): OpenAPI? {
        val info: Info = Info()
                .title("Wetube")
                .version(springdocVersion)
                .description("Wetube api docs as swagger")

        return OpenAPI().components(
                Components().addSecuritySchemes(
                        "Token",
                        SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")
                ))
                .info(info)

    }
}
