FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

EXPOSE 9080

CMD ["java", "-jar", "target/CoffeeBackend-0.0.1-SNAPSHOT.jar"]