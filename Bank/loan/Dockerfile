FROM openjdk:21-jdk-slim

LABEL org.marjoz.image.author="marjoz"

COPY build/libs/loan-0.0.1-SNAPSHOT.jar loan-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "loan-0.0.1-SNAPSHOT.jar"]