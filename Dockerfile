FROM maven:3.9.12-eclipse-temurin-25-alpine AS BUILD
WORKDIR /app
COPY . .

RUN mvn clean install -DskipTest
FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar /app/bff-agendador-de-tarefas.jar
EXPOSE 8083
CMD ["java", "-jar", "/app/bff-agendador-de-tarefas.jar"]