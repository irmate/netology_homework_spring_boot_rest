FROM adoptopenjdk:11-jdk-hotspot
EXPOSE 8080
ADD target/App-AutorizationService-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]