FROM openjdk:18-jdk-alpine3.14
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java", "-jar", "/app.jar"]
LABEL maintainer="lucasteodoroac@gmail.com"

ENV LANG C.UTF-8

RUN apk add --update bash

ADD out/artifacts/beerstore_jar/*.jar /app/app.jar

CMD java -jar /app/app.jar $APP_OPTIONS