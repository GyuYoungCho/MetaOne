FROM openjdk:8-jdk-alpine as builder

WORKDIR application

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/application/application.jar"]



#RUN java -Djarmode=layertools -jar application.jar extract
#
#FROM openjdk:8-jdk-alpine
#WORKDIR application
#ARG DOMAIN
#ENV port 8080
#ENV spring.profiles.active local
#COPY --from=builder application/dependencies/ ./
#COPY --from=builder application/spring-boot-loader/ ./
#COPY --from=builder application/snapshot-dependencies/ ./
#COPY --from=builder application/application/ ./
#RUN echo "$DOMAIN"
#ENV DOMAIN=$DOMAIN
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
# ENTRYPOINT ["java", "-jar", "/application.jar"]
