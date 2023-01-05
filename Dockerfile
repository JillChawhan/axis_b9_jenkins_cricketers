FROM openjdk:8

ADD /target/springboot-demo-0.0.1-SNAPSHOT.jar cricketerdocker.jar

# TO SHOW IN WHICH PORT THE PROJECT IS RUNNING
# WHERE THE REST POINTS ARE TO BE EXECUTED
EXPOSE 8082

ENTRYPOINT [ "java", "-jar", "cricketerdocker.jar" ]