FROM openjdk:17
ENV TZ=Asia/Seoul
COPY /application-infrastructure/build/libs/application-infrastructure-0.0.1-SNAPSHOT.jar app.jar
COPY /datadog/dd-java-agent.jar /usr/agent/dd-java-agent.jar

ARG CLOUD_CONFIG_IMPORT_URL
ENV CLOUD_CONFIG_IMPORT_URL=$CLOUD_CONFIG_IMPORT_URL

ARG APPLICATION_PROFILE
ENV APPLICATION_PROFILE=$APPLICATION_PROFILE

ENTRYPOINT ["java","-javaagent:/usr/agent/dd-java-agent.jar", "-Ddd.agent.host=localhost", "-Ddd.profiling.enabled=true","-XX:FlightRecorderOptions=stackdepth=256", "-Ddd.logs.injection=true", "-Ddd.service=application-api", "-Ddd.env=prod", "-Dspring.profiles.active=production", "-jar", "/app.jar"]
