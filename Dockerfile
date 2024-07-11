FROM openjdk:17-oracle
WORKDIR /app
EXPOSE 6000
COPY ./target/*.jar /app/app2.jar
ENTRYPOINT ["java", "-jar", "/app/app2.jar", "--server.port=6000"]
