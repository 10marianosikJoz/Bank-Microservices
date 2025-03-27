FROM openjdk:21-jdk-slim

LABEL org.marjoz.image.author="marjoz"

RUN apt-get update && apt-get install -y curl && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

COPY build/libs/card-0.0.1-SNAPSHOT.jar card-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "card-0.0.1-SNAPSHOT.jar"]