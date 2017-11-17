FROM openjdk:8
EXPOSE 8051
ADD /target/weather-0.0.1-SNAPSHOT.jar weather-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","weather-0.0.1-SNAPSHOT.jar"]