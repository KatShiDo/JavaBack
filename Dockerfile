FROM openjdk:21

ADD target/JavaBack-1.0.jar /home/server.jar

WORKDIR /home

CMD ["java", "-jar", "server.jar"]