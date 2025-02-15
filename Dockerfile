# Usa una imagen de Java 17 para ejecutar Spring Boot
FROM openjdk:17-jdk-slim
WORKDIR /app  # Carpeta donde se ejecutará el backend

# Copia el archivo JAR (el backend compilado)
COPY target/app_SantaFe.jar app_SantaFe.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app_SantaFe.jar"]