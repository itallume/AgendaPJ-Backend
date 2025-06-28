# Etapa de build com Maven
FROM maven:3.8.5-openjdk-21-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -X

# Etapa de execução
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "--enable-preview", "-jar", "app.jar"]
# Dockerfile para construir e executar uma aplicação Java com Maven