FROM openjdk:21-jdk

LABEL maintainer=UzmaSabir
ENV TZ="Europe/Oslo"
ENV JAVA_OPTS=""

WORKDIR /srv/app
USER root
COPY target/*.jar ./app.jar

USER 1001

EXPOSE 8080

CMD ["java", "-jar", "/srv/app/app.jar"]
