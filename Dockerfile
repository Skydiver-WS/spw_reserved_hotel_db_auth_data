FROM openjdk:24
LABEL authors="Aleksandr"

WORKDIR /app

COPY target/db_auth_service-*.jar app.jar

COPY pom.xml .
COPY src ./src

# Активируем нужные Maven профили
RUN mvn clean package -Pdb-postgres -DskipTests

EXPOSE 8084:8084
ENTRYPOINT ["java", "-jar", "app.jar"]