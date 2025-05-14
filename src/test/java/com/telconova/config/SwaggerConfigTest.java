package com.telconova.config;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.OpenAPI;
import com.telconova.auth.config.SwaggerConfig;

import java.lang.reflect.Field;

class SwaggerConfigTest {

    @Test
    void testCustomOpenAPI() throws Exception {
        SwaggerConfig config = new SwaggerConfig();

        // Asignar valores a los campos privados usando reflexión
        setField(config, "apiTitle", "API Documentation");
        setField(config, "apiVersion", "1.0");
        setField(config, "apiDescription", "Test Description");
        setField(config, "termsUrl", "https://example.com/terms");
        setField(config, "contactName", "Support Team");
        setField(config, "contactEmail", "support@example.com");
        setField(config, "contactUrl", "https://example.com/contact");
        setField(config, "licenseName", "Apache 2.0");
        setField(config, "licenseUrl", "https://www.apache.org/licenses/LICENSE-2.0.html");
        setField(config, "productionServerUrl", "https://api.example.com");
        setField(config, "stagingServerUrl", "https://staging-api.example.com");

        OpenAPI openAPI = config.customOpenAPI();

        assertNotNull(openAPI.getInfo());
        assertEquals("API Documentation", openAPI.getInfo().getTitle());
        assertEquals("1.0", openAPI.getInfo().getVersion());
    }

    // Método auxiliar para establecer campos privados
    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}