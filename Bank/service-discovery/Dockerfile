FROM openjdk:21-jdk-slim

LABEL org.marjoz.image.author="marjoz"

COPY build/libs/service-discovery-0.0.1-SNAPSHOT.jar service-discovery-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "service-discovery-0.0.1-SNAPSHOT.jar"]