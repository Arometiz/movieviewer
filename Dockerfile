FROM openjdk:8-jdk
EXPOSE 3030
ARG JAR_FILE=/var/lib/jenkins/workspace/target/movieviewer-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]