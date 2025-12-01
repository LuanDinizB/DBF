FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

COPY pom.xml .

RUN apt-get update && apt-get install -y maven && \
    mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

EXPOSE 8181

COPY --from=build /app/target/escolar-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]