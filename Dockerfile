FROM eclipse-temurin:17

LABEL mentainer="amadeuscam@yahoo.es"

WORKDIR /app

COPY target/datacentric-0.0.1-SNAPSHOT.jar /app/datacentric.jar

ENTRYPOINT ["java", "-jar", "datacentric.jar"]
