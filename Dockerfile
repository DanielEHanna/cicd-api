FROM eclipse-temurin:17-jdk-focal

EXPOSE 8080

WORKDIR /applications

COPY target/springboot-cicd-0.0.1-SNAPSHOT.jar /applications/springboot-cicd.jar

ENTRYPOINT ["java","-jar", "springboot-cicd.jar"]