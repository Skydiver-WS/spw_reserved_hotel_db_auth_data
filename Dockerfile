FROM openjdk:24
LABEL authors="Aleksandr"

WORKDIR /app

COPY target/db_auth_service-*.jar app.jar

EXPOSE 8084:8084
ENV SPRING_PROFILES_ACTIVE=pg
ENTRYPOINT ["java", "-jar", "app.jar"]