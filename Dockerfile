FROM maven:3.9-amazoncorretto-23 as builder
LABEL authors="Aleksandr"

WORKDIR /app

COPY target/db_auth_service-*.jar app.jar

COPY pom.xml .
COPY src ./src

# Активируем нужные Maven профили
RUN mvn clean package -Pdb-postgres,pg -DskipTests

EXPOSE 8084:8084
ENV SPRING_PROFILES_ACTIVE=db-postgres
ENTRYPOINT ["java", "-jar", "app.jar"]