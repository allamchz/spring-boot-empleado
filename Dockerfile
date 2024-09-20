FROM alpine/java:21-jdk
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#docker  build -t  eureka .
#docker run -p 8761:8761 eureka
#docker login
#docker tag micro  allamchz/eureka:latest
#docker push allamchz/eureka:latest