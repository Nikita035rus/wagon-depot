FROM openjdk:18
ADD /target/tuktuk-0.0.1-SNAPSHOT.jar beckend.jar
ENTRYPOINT ["java", "-jar", "beckend.jar"]
