FROM openjdk:8 as builder
WORKDIR /opt/app
COPY .mvn .mvn
COPY mvnw ./
COPY pom.xml ./
RUN chmod +x ./mvnw
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY /src ./src
# skip test to save minutes on github workflows
RUN ./mvnw clean install -Dmaven.test.skip=true


FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 7000
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]