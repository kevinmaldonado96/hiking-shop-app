# Etapa 1: build con Maven
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

COPY . .

RUN mvn -f pom.xml clean package -DskipTests=true

# Etapa 2: imagen final ligera con solo el JAR
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app

# Copiamos el .jar compilado desde la etapa "builder"
COPY --from=builder /app/target/hiking-shop-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
