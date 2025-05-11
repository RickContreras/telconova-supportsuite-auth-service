package com.telconova.config;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.OpenAPI;
import com.telconova.auth.config.SwaggerConfig;
class SwaggerConfigTest {

    @Test
    void testCustomOpenAPI() {
        SwaggerConfig config = new SwaggerConfig();
        OpenAPI openAPI = config.customOpenAPI();

        assertNotNull(openAPI.getInfo());
        assertEquals("API Documentation", openAPI.getInfo().getTitle());
        assertEquals("1.0", openAPI.getInfo().getVersion());
    }
}