FROM alpine/java:21-jdk
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#docker  build -t  empleado .
#docker run --name empleado --network red_paradigmas  -p 8081:8081 empleado
#docker login
#docker tag micro  allamchz/empleado:latest
#docker push allamchz/empleado:latest