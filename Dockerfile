# Stage 1 build the application

FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /Blog-Application
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# stage 2 run the application

FROM openjdk:17-alpine
WORKDIR /Blog-Application
COPY --from=build /Blog-Application/target/blog-app-apis-0.0.1-SNAPSHOT.jar ./blog-app-apis.jar
EXPOSE 8080
CMD ["java", "-jar", "blog-app-apis.jar"]