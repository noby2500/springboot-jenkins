FROM openjdk:17-jdk-alpine
ENV TZ=Asia/Bangkok
RUN apk update && apk add tzdata
ADD target/springboot-jenkins.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
