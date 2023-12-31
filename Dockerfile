FROM maven:3.8.3-openjdk-17 AS MAVEN_BUILD
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
RUN mvn clean package
CMD ["java", "-jar", "target/wagon-depot-0.0.1-SNAPSHOT.jar"]