FROM gradle:8.7-jdk AS build

WORKDIR /application
COPY . .
RUN ./gradlew clean build -x test

FROM eclipse-temurin:latest AS run

RUN adduser --system --group app-user

WORKDIR /opt/app

COPY --from=build --chown=app-user:app-user /application/build/libs/*.jar app.jar

USER app-user

ENTRYPOINT ["java", "-jar", "app.jar"]