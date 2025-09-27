FROM openjdk:24
LABEL authors="Aleksandr"

WORKDIR /app

COPY target/db_auth_service-*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]