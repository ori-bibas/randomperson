FROM openjdk:8
EXPOSE 8080
ADD target/randomperson-api.jar randomperson-api.jar
ENTRYPOINT ["java", "-jar", "/randomperson-api.jar"]