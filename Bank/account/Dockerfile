FROM openjdk:21-jdk-slim

LABEL org.marjoz.image.author="marjoz"

COPY build/libs/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "account-0.0.1-SNAPSHOT.jar"]