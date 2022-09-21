FROM bellsoft/liberica-openjdk-alpine:11.0.13
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080
#ENV DATABASE_HOST localhost
#ENV DATABASE_PORT 5432

ENTRYPOINT ["java","-jar","/app.jar"]
