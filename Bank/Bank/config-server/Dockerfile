FROM openjdk:21-jdk-slim

LABEL org.marjoz.image.author="marjoz"

COPY build/libs/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]