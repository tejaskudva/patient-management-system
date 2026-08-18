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
COPY --from=builder ./app/target/pm-patient-service.jar ./pm-patient-service.jar
EXPOSE 4000
ENTRYPOINT [ "java", "-jar", "pm-patient-service.jar" ]