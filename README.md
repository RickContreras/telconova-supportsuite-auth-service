> 🚧 En desarrollo

# 🔒 Microservicio de Gestión de Usuarios y Autenticación

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Security](https://img.shields.io/badge/Security-OWASP%20Top%2010-brightgreen)
![Status](https://img.shields.io/badge/Status-En%20Desarrollo-yellow)
![License](https://img.shields.io/badge/License-Apache%202.0-blue)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)
![GraphQL](https://img.shields.io/badge/GraphQL-Apollo-ff69b4)

---


## 🌟 Descripción

Este microservicio gestiona toda la autenticación, autorización y administración de usuarios en el sistema TelcoNova SupportSuite. Maneja el registro de usuarios, asignación de roles, gestión de perfiles y control de acceso a las distintas funcionalidades del sistema.

Diseñado para cumplir con los estándares OWASP Top 10 2023, proporciona una solución robusta y segura para la gestión de identidades, completamente integrado con la arquitectura de microservicios de la plataforma.

---

## 🚀 Funcionalidades Principales

- **Autenticación segura con JWT** y roles predefinidos: `ADMIN`, `TECNICO`, `CLIENTE`
- **Gestión completa de usuarios** (registro, actualización, eliminación)
- **Control de acceso basado en roles (RBAC)** con permisos granulares
- **Autenticación multifactor (MFA)** con Google Authenticator y SMS (Opcional)
- **Recuperación y cambios de contraseña** seguros
- **Cifrado AES-256** para datos sensibles
- **Rate Limiting** para prevenir ataques de fuerza bruta
- **Registro de actividad** y auditoría completa
- **API flexible** con endpoints REST y GraphQL

---

## 🛠️ Tecnologías Utilizadas

| Categoría          | Tecnologías                                        |
|--------------------|---------------------------------------------------|
| **Backend**        | Spring Boot <V>, Spring Security, Spring Data JPA |
| **Base de Datos**  | PostgreSQL <V>, Redis (blacklist de tokens y caché)|
| **APIs**           | REST, GraphQL (Apollo)                            |
| **Seguridad**      | JWT, OAuth2, BCrypt, HTTPS/TLS 1.3               |
| **Mensajería**     | RabbitMQ (compatible con AWS SNS/SQS y Azure Service Bus) |
| **Monitoreo**      | Prometheus, Grafana, ELK Stack (Elasticsearch, Logstash, Kibana) |
| **Almacenamiento** | AWS S3/Azure Blob Storage para exportaciones y backups |
| **Proxy Inverso**  | Nginx como entrada al microservicio |
| **Orquestación**   | Kubernetes con Helm charts |
| **CI/CD**          | GitHub Actions, SonarQube |

---

## 📁 Estructura del Proyecto

```
telconova-supportsuite-auth-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── telconova/
│   │   │           └── auth/
│   │   │               ├── config/                  # Configuraciones globales
│   │   │               │   ├── SecurityConfig.java      # Configuración de Spring Security
│   │   │               │   ├── GraphQLConfig.java       # Configuración de GraphQL
│   │   │               │   ├── RedisConfig.java         # Configuración de Redis (JWT blacklist)
│   │   │               │   ├── RabbitMQConfig.java      # Configuración de RabbitMQ (eventos MFA)
│   │   │               │   ├── ElasticConfig.java       # Configuración de Elasticsearch para logging
│   │   │               │   └── StorageConfig.java       # Configuración de blob storage
│   │   │               │
│   │   │               ├── controller/             # Controladores REST y GraphQL
│   │   │               │   ├── AuthController.java     # Endpoints REST (/api/v1/auth/**)
│   │   │               │   └── UserGraphQLController.java  # Resolvers de GraphQL
│   │   │               │
│   │   │               ├── service/                # Lógica de negocio
│   │   │               │   ├── AuthService.java        # Autenticación, JWT, MFA
│   │   │               │   ├── UserService.java        # Gestión de usuarios y roles
│   │   │               │   ├── MfaService.java         # Lógica de autenticación multifactor
│   │   │               │   └── StorageService.java     # Manejo de blob storage
│   │   │               │
│   │   │               ├── repository/             # Acceso a datos
│   │   │               │   ├── UserRepository.java     # Spring Data JPA (PostgreSQL)
│   │   │               │   └── RoleRepository.java     # Repositorio de roles
│   │   │               │
│   │   │               ├── model/                  # Entidades y DTOs
│   │   │               │   ├── User.java               # Entidad Usuario (JPA)
│   │   │               │   ├── Role.java               # Entidad Rol
│   │   │               │   ├── AuthRequest.java        # DTO para login
│   │   │               │   └── AuthResponse.java       # DTO con JWT
│   │   │               │
│   │   │               ├── security/               # Configuración de seguridad
│   │   │               │   ├── JwtAuthFilter.java         # Filtro para validar JWT
│   │   │               │   ├── JwtUtils.java              # Generación/validación de tokens
│   │   │               │   └── CustomUserDetails.java     # Implementación de UserDetails
│   │   │               │
│   │   │               ├── exception/              # Manejo de excepciones
│   │   │               │   ├── GlobalExceptionHandler.java  # @ControllerAdvice
│   │   │               │   └── AuthException.java          # Excepciones personalizadas
│   │   │               │
│   │   │               └── event/                  # Eventos y mensajería
│   │   │                   ├── UserEventPublisher.java    # Publicador RabbitMQ (user.created)
│   │   │                   └── MfaEventConsumer.java      # Consumidor para notificaciones MFA
│   │   │
│   │   └── resources/
│   │       ├── graphql/                    # Esquemas GraphQL
│   │       │   └── user.graphqls           # Definiciones de queries/mutations
│   │       │
│   │       ├── static/                     # Recursos estáticos (opcional)
│   │       ├── templates/                  # Plantillas (email MFA, etc.)
│   │       │
│   │       ├── application.yml             # Configuración común
│   │       ├── application-dev.yml         # Configuración desarrollo
│   │       ├── application-prod.yml        # Configuración producción
│   │       └── logback-spring.xml          # Configuración de logs (formato JSON para ELK)
│   │
│   └── test/                              # Pruebas
│       └── java/com/telconova/auth/
│           ├── controller/
│           ├── service/
│           └── security/                  # Pruebas de seguridad
│
├── docker/                                # Configuración Docker 
│   ├── Dockerfile
│   └── docker-compose.yml                 # Servicios: Postgres, Redis, RabbitMQ, Elasticsearch
│
├── k8s/                                  # Manifiestos Kubernetes
│   ├── deployment.yaml
│   ├── service.yaml
│   ├── ingress.yaml                      # Configuración de Nginx Ingress
│   ├── configmap.yaml
│   └── secrets.yaml
│
├── helm/                                 # Charts de Helm para despliegue en K8s
│   └── ms-usuarios/
│
├── nginx/                                # Configuración de Nginx
│   └── nginx.conf                        # Configuración base para proxy inverso
│
├── .github/
│   └── workflows/                        # Workflows de GitHub Actions
│       ├── ci.yml                        # Integración continua
│       └── cd.yml                        # Despliegue continuo
│
├── .gitignore
├── pom.xml                                # Dependencias Maven
├── README.md                              # Documentación principal
└── Jenkinsfile                            # Pipeline CI/CD (opcional)
```

## 📋 Estructura de Base de Datos

### Tabla: `usuario`
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_usuario | INT | Identificador único (PK) |
| username | VARCHAR(50) | Nombre de usuario único |
| email | VARCHAR(100) | Correo electrónico del usuario |
| password_hash | VARCHAR(255) | Hash de la contraseña (BCrypt) |
| nombre | VARCHAR(100) | Nombre completo del usuario |
| telefono | VARCHAR(20) | Número de teléfono |
| fecha_registro | DATETIME | Fecha de registro del usuario |
| activo | BOOLEAN | Estado del usuario (activo/inactivo) |
| ultimo_login | DATETIME | Fecha y hora del último inicio de sesión |
| mfa_enabled | BOOLEAN | Estado de autenticación multifactor |
| mfa_secret | VARCHAR(255) | Secreto cifrado para MFA (cuando está habilitado) |

### Tabla: `rol`
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_rol | INT | Identificador único (PK) |
| nombre | VARCHAR(50) | Nombre del rol |
| descripcion | VARCHAR(255) | Descripción del rol |

### Tabla: `rol_usuario`
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_rol_usuario | INT | Identificador único (PK) |
| id_rol | INT | Identificador único del Rol (FK) |
| id_usuario | INT | Identificador único del Usuario (FK) |
| fecha_asignacion | DATETIME | Fecha de registro del usuario |

### Tabla: `permiso`
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_permiso | INT | Identificador único (PK) |
| permiso | VARCHAR(100) | Código del permiso (ej: `leer:ordenes`) |
| descripcion | VARCHAR(255) | Descripción del permiso | |

### Tabla: `permisos_rol`
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_permiso_rol | INT | Identificador único (PK) |
| id_rol | INT | Rol asociado (FK) |
| id_permiso | INT | Permiso asociado (FK) |

---

## 📡 Endpoints API

### Autenticación (REST)
- `POST /api/v1/auth/login` - Inicio de sesión
- `POST /api/v1/auth/logout` - Cierre de sesión
- `POST /api/v1/auth/refresh-token` - Renovar token JWT
- `POST /api/v1/auth/forgot-password` - Solicitar recuperación de contraseña
- `POST /api/v1/auth/reset-password` - Restablecer contraseña
- `POST /api/v1/auth/mfa/enable` - Activar autenticación multifactor
- `POST /api/v1/auth/mfa/verify` - Verificar código MFA

### Usuarios (REST)
- `GET /api/v1/users` - Listar usuarios
- `GET /api/v1/users/{id}` - Obtener usuario por ID
- `POST /api/v1/users` - Crear nuevo usuario
- `PUT /api/v1/users/{id}` - Actualizar usuario
- `DELETE /api/v1/users/{id}` - Eliminar usuario
- `GET /api/v1/users/me` - Obtener perfil del usuario autenticado
- `PUT /api/v1/users/me` - Actualizar perfil propio

### Roles y Permisos (REST)
- `GET /api/v1/roles` - Listar roles
- `POST /api/v1/roles` - Crear nuevo rol
- `PUT /api/v1/roles/{id}` - Actualizar rol
- `DELETE /api/v1/roles/{id}` - Eliminar rol
- `GET /api/v1/roles/{id}/permissions` - Listar permisos de un rol
- `POST /api/v1/roles/{id}/permissions` - Asignar permisos a un rol

### GraphQL API
Disponible en `/graphql/v1` para consultas flexibles:

```graphql
query GetUserWithRoles {
  usuario(email: "user@telconova.com") {
    id
    email
    roles { 
      nombre 
      permisos 
    }
  }
}
```
### Swagger

Disponible en `/swagger-ui/index.html` o `/swagger-ui.html`

### Actuator

Disponible en `/actuator/health`
---

## ⚙️ Configuración

### Variables de Entorno (`.env`)
```
# Servidor
MS_USUARIOS_PORT=8080

# Seguridad
JWT_SECRET=tu_clave_super_secreta_rotativa
JWT_EXPIRATION_MS=86400000  # 24h
MFA_ENABLED=true

# PostgreSQL
DB_URL=jdbc:postgresql://postgres-auth:5432/telconova_usuarios
DB_USER=auth_admin
DB_PASSWORD=*****

# Redis
REDIS_HOST=redis-auth
REDIS_PORT=6379
REDIS_PASSWORD=*****

# Twilio (MFA SMS)
TWILIO_SID=ACXXXXXX
TWILIO_TOKEN=*******

# RabbitMQ (Mensajería)
RABBITMQ_HOST=rabbitmq
RABBITMQ_PORT=5672
RABBITMQ_USER=rabbitmq_user
RABBITMQ_PASSWORD=*****

# Elasticsearch (Logging)
ELASTIC_HOST=elasticsearch
ELASTIC_PORT=9200
ELASTIC_USER=elastic
ELASTIC_PASSWORD=*****

# Blob Storage
STORAGE_TYPE=azure  # o 'aws' para S3
AZURE_STORAGE_CONNECTION_STRING=*****
# o para AWS:
# AWS_ACCESS_KEY=*****
# AWS_SECRET_KEY=*****
# AWS_BUCKET_NAME=telconova-auth

# Cloud Environment
CLOUD_PROVIDER=none  # 'aws', 'azure' o 'none'

# Swagger
SWAGGER_PRODUCTION_SERVER_URL=https://api.telconova.com/usuarios
```

---

## 📊 Eventos (Mensajería)

### Eventos Publicados
- `user.created` - Cuando se crea un nuevo usuario
- `user.updated` - Cuando se actualiza un usuario
- `user.deleted` - Cuando se elimina un usuario
- `user.login` - Cuando un usuario inicia sesión
- `user.logout` - Cuando un usuario cierra sesión
- `role.changed` - Cuando cambian los roles de un usuario
- `mfa.enabled` - Cuando un usuario activa MFA
- `mfa.verified` - Cuando un usuario verifica MFA

### Eventos Consumidos
- `system.startup` - Para inicializar roles y usuarios por defecto
- `password.reset.requested` - Para procesar solicitudes de reinicio

### Compatibilidad con Cloud
El servicio está diseñado para una fácil migración entre:
- RabbitMQ (on-premise)
- AWS SNS/SQS 
- Azure Service Bus

La configuración apropiada se activa mediante variables de entorno.

---

## 🛡️ Políticas de Seguridad

- **Cifrado**: 
  - TLS 1.3 para todas las comunicaciones
  - AES-256 para datos sensibles en PostgreSQL
  - Contraseñas hasheadas con BCrypt
  
- **RBAC**:
  - Control de acceso basado en roles con anotaciones de seguridad
  - `@PreAuthorize("hasRole('ADMIN')")` para endpoints protegidos
  
- **Protección contra ataques**:
  - Rate limiting (5 intentos de login fallidos por IP/minuto)
  - Bloqueo de cuentas tras múltiples intentos fallidos
  - Validación de complejidad de contraseñas
  - Protección contra ataques de fuerza bruta
  
- **Autenticación**:
  - JWT con rotación de tokens
  - Autenticación multifactor (opcional)
  - Control de sesiones activas mediante Redis
  
- **Auditoría**:
  - Logs estructurados en formato JSON enviados a Elasticsearch
  - Registro detallado de actividades de usuario
  - Dashboards en Kibana para análisis de seguridad

---

## 📈 Métricas y Monitoreo

### Métricas Principales
- Tasas de éxito/fracaso de inicios de sesión
- Número de usuarios activos
- Tiempos de respuesta de autenticación (<500ms P99)
- Intentos fallidos de inicio de sesión
- Disponibilidad: 99.9%
- Uso de CPU/memoria/red
- Latencia de operaciones de base de datos

### Stack de Observabilidad
- **Prometheus**: Recopilación de métricas
- **Grafana**: Dashboards de visualización
  - Dashboard principal: `http://monitoring.telconova.local/d/auth-metrics`
  - Dashboard de seguridad: `http://monitoring.telconova.local/d/auth-security`
- **Elasticsearch**: Almacenamiento centralizado de logs
- **Kibana**: Visualización y análisis de logs (Opcional)
- **Alertmanager**: Configuración de alertas basadas en métricas (Opcional)

### Exportadores
- Spring Boot Actuator para métricas de aplicación
- Prometheus JMX Exporter para métricas JVM
- PostgreSQL Exporter para métricas de base de datos
- Redis Exporter para métricas de caché

---

## 🚀 Despliegue

### Docker
```bash
# Construir la imagen Docker
docker build -t telconova/ms-usuarios:latest .

# Ejecutar el contenedor
docker run -d --name ms-usuarios \
  -p 8080:8080 \
  --env-file .env \
  telconova/ms-usuarios:latest
```

### Docker Compose (Desarrollo)
```bash
docker-compose -f docker-compose-auth.yml up --build
```

### Kubernetes (Producción)
```bash
# Usando kubectl directamente
kubectl apply -f k8s/

# O usando Helm
helm install ms-usuarios ./helm/ms-usuarios
```

### Configuración de Nginx (Ingress)
Nginx se utiliza como proxy inverso para todos los microservicios:

```
# Fragmento de configuración en nginx.conf
server {
    listen 80;
    server_name api.telconova.com;
    
    location /api/v1/auth/ {
        proxy_pass http://ms-usuarios:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        # Configuración adicional...
    }
    
    location /graphql/v1 {
        proxy_pass http://ms-usuarios:8080/graphql/v1;
        # Configuración adicional...
    }
}
```

En entornos Kubernetes, se configura a través de Ingress:
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: ms-usuarios-ingress
  annotations:
    kubernetes.io/ingress.class: nginx
    nginx.ingress.kubernetes.io/rewrite-target: /$1
spec:
  rules:
  - host: api.telconova.com
    http:
      paths:
      - path: /api/v1/auth/(.*)
        pathType: Prefix
        backend:
          service:
            name: ms-usuarios
            port:
              number: 8080
```

---
### Ejecución local

Una vez creado el archivo .env ejecutar el siguiente comando:

```bash
export $(cat .env | xargs)
```

Luego ejecute la instalacion de paquetes y corra el proyecto.

```bash
mvn clean install
mvn spring-boot:run
```
---

## 🧪 Pruebas

```bash
# Ejecutar pruebas unitarias
./mvnw test

# Ejecutar pruebas de integración
./mvnw integration-test

# Pruebas de seguridad (OWASP ZAP)
zap-cli quick-scan http://localhost:8080

# Análisis de calidad de código
./mvnw sonar:sonar -Dsonar.host.url=http://sonarqube:9000
```

Cobertura de pruebas: --%

---

## 🔄 Integración con Otras Soluciones

### Almacenamiento
- **Cloud Storage**: Compatible con Azure Blob Storage y AWS S3
- Usado para:
  - Exportación de logs
  - Backups de configuración
  - Almacenamiento de datos de auditoría a largo plazo

### Servicios Cloud
Se espera que el microservicio está diseñado para funcionar en múltiples entornos:
- **On-premise**: Con Docker + Kubernetes
- **AWS**: EKS, RDS, ElastiCache, SQS/SNS
- **Azure**: AKS, Azure SQL, Redis Cache, Service Bus

### Integración con IAM externo
- Soporte para federación con Azure AD / AWS Cognito
- SAML para SSO empresarial

---

## 📌 Mejores Prácticas Implementadas

- **Documentación**: Swagger disponible en `/swagger-ui`
- **Versionado de APIs**: `/api/v1/**` y `/graphql/v1`
- **CI/CD**: Pipelines con GitHub Actions y escaneo de vulnerabilidades (Snyk)
- **Secret Management**: Uso de HashiCorp Vault para claves sensibles
- **Cumplimiento**:
  - ✅ Estándares OWASP Top 10 2023
  - ✅ Requisitos No Funcionales de Seguridad
  - ✅ Políticas de Desarrollo Seguro

---

## 📥 Recursos Adicionales

- [Documentación de Arquitectura](docs/architecture.md)
- [Lineamientos de Seguridad](docs/security.md)
- [Políticas de Desarrollo](docs/development.md)
- [Guía de Despliegue en Kubernetes](docs/k8s-deployment.md)
- [Guía de Migración a Cloud](docs/cloud-migration.md)

---