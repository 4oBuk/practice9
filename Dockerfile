FROM maven:3.8.3-openjdk-17 as build
COPY . /app
WORKDIR /app
# add environment variables after implmenting task because you have to add
# variables implicitly to the app, not using env variables in .properties file
RUN mvn clean package -Dtest.var=$TEST_VAR
FROM openjdk:17-alpine as base
EXPOSE 8080
WORKDIR /app
COPY --from=build /app/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
