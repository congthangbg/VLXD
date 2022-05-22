FROM openjdk:8-jdk-alpine
RUN mkdir demospring
ADD *.jar VLXD-0.0.1-SNAPSHOT.jar
WORKDIR demospring
RUN "pwd"
RUN "ls"
EXPOSE 8080
ENTRYPOINT [ "java","-jar","VLXD-0.0.1-SNAPSHOT.jar" ]
