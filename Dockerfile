FROM maven:3.6.3-openjdk-11 AS builder
WORKDIR /app
#RUN --mount=target=. --mount=type=cache,target=/root/.m2  mvn package -DoutputDirectory=/
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package -DskipTests

FROM openjdk:11.0.10-jre-slim
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
