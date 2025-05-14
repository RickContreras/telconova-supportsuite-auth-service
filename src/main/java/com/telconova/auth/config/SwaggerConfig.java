package com.telconova.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/**
 * Configuración de Swagger/OpenAPI para la documentación de la API.
 */
@Configuration
public class SwaggerConfig {

    @Value("${swagger.api.title:API de Gestión de Usuarios}")
    private String apiTitle;

    @Value("${swagger.api.version:1.0}")
    private String apiVersion;

    @Value("${swagger.api.description:API para la gestión de usuarios y autenticacion}")
    private String apiDescription;

    @Value("${swagger.terms-url:https://example.com/terms}")
    private String termsUrl;

    @Value("${swagger.contact.name:Support Team}")
    private String contactName;

    @Value("${swagger.contact.email:support@example.com}")
    private String contactEmail;

    @Value("${swagger.contact.url:https://example.com/contact}")
    private String contactUrl;

    @Value("${swagger.license.name:Apache 2.0}")
    private String licenseName;

    @Value("${swagger.license.url:https://www.apache.org/licenses/LICENSE-2.0.html}")
    private String licenseUrl;

    @Value("${swagger.production-server-url:https://api.example.com}")
    private String productionServerUrl;

    @Value("${swagger.staging-server-url:https://staging-api.example.com}")
    private String stagingServerUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title(apiTitle)
                        .version(apiVersion)
                        .description(apiDescription)
                        .termsOfService(termsUrl)
                        .contact(new Contact()
                                .name(contactName)
                                .email(contactEmail)
                                .url(contactUrl))
                        .license(new License()
                                .name(licenseName)
                                .url(licenseUrl))
                )
                .servers(List.of(
                        new Server()
                                .url(productionServerUrl)
                                .description("Production Server"),
                        new Server()
                                .url(stagingServerUrl)
                                .description("Staging Server")
                ))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}