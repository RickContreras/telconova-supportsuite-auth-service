# Usar una imagen base de OpenJDK
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado por Maven al contenedor
COPY target/auth-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que corre la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]