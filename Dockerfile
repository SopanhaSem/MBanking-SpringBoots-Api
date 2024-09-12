# PHASE 1 INSTALL JDK
FROM ghcr.io/graalvm/jdk-community:21
WORKDIR app
ADD ./build/libs/spring-banking-api-1.0.jar /app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/spring-banking-api-1.0.jar"]