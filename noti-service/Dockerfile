FROM openjdk:17-jdk

ARG JAR_FILE=build/libs/*.jar
ARG PINPOINT_JAR_FILE=../pinpoint/pinpoint-agent-2.2.2/pinpoint-bootstrap-2.2.2.jar
ARG PINPOINT_CONFIG=../pinpoint/pinpoint-agent-2.2.2/pinpoint-root.config

COPY ${JAR_FILE} app.jar
COPY ${PINPOINT_JAR_FILE} pinpoint.jar
COPY ${PINPOINT_CONFIG} pinpoint-root.config

ENTRYPOINT ["java", "-javaagent:pinpoint.jar", "-Dpinpoint.agentId=notiService", "-Dpinpoint.config=pinpoint-root.config",  "-Dspring.profiles.active=prod", "-Duser.timezone=Asia/Seoul" , "-jar", "app.jar"]
