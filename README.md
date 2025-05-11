# Telconova Support Suite Auth Service

## 🚀 Configuración del Proyecto

Este proyecto utiliza Spring Boot, Swagger/OpenAPI y otras herramientas para proporcionar un servicio de autenticación robusto.

### 📋 Requisitos Previos

- **Java 21**: Asegúrate de tener instalado Java 21 o superior.
- **Maven**: Para gestionar las dependencias y construir el proyecto.
- **Docker** (opcional): Para ejecutar servicios como PostgreSQL o Redis si están configurados.

### ⚙️ Configuración de Variables de Entorno

El proyecto utiliza variables de entorno para personalizar la configuración de Swagger/OpenAPI. Estas variables se definen en un archivo `.env` en la raíz del proyecto.

#### Variables de Entorno Requeridas

| Variable                        | Descripción                                      | Valor por Defecto                     |
|---------------------------------|--------------------------------------------------|---------------------------------------|
| `SWAGGER_PRODUCTION_SERVER_URL` | URL del servidor de producción para Swagger      | `https://api.example.com`            |
| `SWAGGER_STAGING_SERVER_URL`    | URL del servidor de staging para Swagger         | `https://staging-api.example.com`    |
| `SWAGGER_API_TITLE`             | Título de la API en la documentación de Swagger | `API Documentation`                  |
| `SWAGGER_API_VERSION`           | Versión de la API                                | `1.0`                                |
| `SWAGGER_API_DESCRIPTION`       | Descripción de la API                            | `Documentación de la API de ejemplo` |

#### Ejemplo de Archivo `.env`

Crea un archivo `.env` en la raíz del proyecto con el siguiente contenido:

```properties
SWAGGER_PRODUCTION_SERVER_URL=https://bookish-doodle-4p9gw64769ph7x75-8080.app.github.dev
SWAGGER_STAGING_SERVER_URL=https://staging-api.example.com
SWAGGER_API_TITLE=Telconova Auth API
SWAGGER_API_VERSION=1.0
SWAGGER_API_DESCRIPTION=API para la gestión de autenticación en Telconova