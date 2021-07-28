FROM openjdk:latest

COPY /target/libs /libs
COPY target/KuehneNageTestTask-*.jar /TrialTask.jar

CMD ["java", "-jar", "/TrialTask.jar"]