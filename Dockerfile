# Etapa de construcción
FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:21-jdk-slim
WORKDIR /app

# Crear usuario no privilegiado
RUN addgroup --system --gid 1001 appuser && \
    adduser --system --uid 1001 --gid 1001 appuser

# Copiar solo el JAR del paso anterior
COPY --from=build /app/target/auth-0.0.1-SNAPSHOT.jar app.jar

# Cambiar al usuario no privilegiado
USER appuser

# Configuración de JVM optimizada
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080

# Healthcheck
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Comando con opciones optimizadas
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar