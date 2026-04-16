FROM eclipse-temurin:21-jdk-jammy AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN chmod +x ./gradlew
RUN ./gradlew build -x test --no-daemon

FROM eclipse-temurin:21-jre-jammy 
COPY --from=build /usr/app/build/libs/app.jar app.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx300m", "-jar", "/app.jar"]
