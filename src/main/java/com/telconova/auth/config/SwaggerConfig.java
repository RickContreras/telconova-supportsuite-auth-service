package com.telconova.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Configuración de Swagger/OpenAPI para la documentación de la API.
 * 
 * Esta clase utiliza variables de entorno para personalizar la configuración
 * de la documentación, como el título, la descripción, los servidores y la información
 * de contacto. Permite generar documentación interactiva para los consumidores de la API.
 */
@Configuration
public class SwaggerConfig {

    private final Dotenv dotenv = Dotenv.load();

    /**
     * Obtiene el valor de una variable de entorno o devuelve un valor predeterminado si no está definida.
     *
     * @param key          La clave de la variable de entorno.
     * @param defaultValue El valor predeterminado a usar si la variable no está definida.
     * @return El valor de la variable de entorno o el valor predeterminado.
     */
    private String getEnvOrDefault(String key, String defaultValue) {
        String value = dotenv.get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * Configura la instancia de OpenAPI para la documentación de la API.
     *
     * Este método utiliza variables de entorno para personalizar la información de la API,
     * como el título, la versión, la descripción, los términos de servicio, la información
     * de contacto y los servidores disponibles.
     *
     * @return Una instancia personalizada de {@link OpenAPI}.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Información general de la API
                .info(new Info()
                        .title(getEnvOrDefault("SWAGGER_API_TITLE", "API Documentation"))
                        .version(getEnvOrDefault("SWAGGER_API_VERSION", "1.0"))
                        .description(getEnvOrDefault("SWAGGER_API_DESCRIPTION", "Documentación de la API de ejemplo"))
                        .termsOfService(getEnvOrDefault("SWAGGER_TERMS_URL", "https://example.com/terms"))
                        .contact(new Contact()
                                .name(getEnvOrDefault("SWAGGER_CONTACT_NAME", "Support Team"))
                                .email(getEnvOrDefault("SWAGGER_CONTACT_EMAIL", "support@example.com"))
                                .url(getEnvOrDefault("SWAGGER_CONTACT_URL", "https://example.com/contact")))
                        .license(new License()
                                .name(getEnvOrDefault("SWAGGER_LICENSE_NAME", "Apache 2.0"))
                                .url(getEnvOrDefault("SWAGGER_LICENSE_URL", "https://www.apache.org/licenses/LICENSE-2.0.html")))
                )
                // Configuración de los servidores
                .servers(List.of(
                        new Server()
                                .url(getEnvOrDefault("SWAGGER_PRODUCTION_SERVER_URL", "https://api.example.com"))
                                .description("Production Server"),
                        new Server()
                                .url(getEnvOrDefault("SWAGGER_STAGING_SERVER_URL", "https://staging-api.example.com"))
                                .description("Staging Server")
                ));
    }
}