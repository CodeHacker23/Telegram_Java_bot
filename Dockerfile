FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/bot.jar /app/bot.jar
ENTRYPOINT ["java","-jar","/app/bot.jar"]

