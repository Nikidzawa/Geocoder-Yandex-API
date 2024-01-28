FROM openjdk:21

ARG GEO=*.jar

COPY ${GEO} geo.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "geo.jar"]