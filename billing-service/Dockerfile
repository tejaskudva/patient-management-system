# Build jar using maven
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Copy jar to directory
FROM eclipse-temurin:21-jdk AS runner
WORKDIR /app
COPY --from=builder ./app/target/pm-billing-service.jar ./pm-billing-service.jar
EXPOSE 4001
EXPOSE 9001
ENTRYPOINT [ "java", "-jar", "pm-billing-service.jar" ]