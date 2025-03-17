FROM openjdk:21-jdk-slim

LABEL org.marjoz.image.author="marjoz"

COPY build/libs/card-0.0.1-SNAPSHOT.jar card-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "card-0.0.1-SNAPSHOT.jar"]