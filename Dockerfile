FROM openjdk:11-jre-slim

EXPOSE 8080

ADD build/libs/view.jar /view.jar
VOLUME ["/pivio-conf"]

CMD ["java", "-jar", "/view.jar"]
