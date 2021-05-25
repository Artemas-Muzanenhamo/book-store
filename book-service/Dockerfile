FROM gradle:jdk11 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle -x test build --no-daemon

FROM adoptopenjdk/openjdk11 AS book-service
# copy JAR into image
COPY --from=build /home/gradle/src/build/libs/book-service-*-SNAPSHOT.jar /book-service.jar
# expose port
EXPOSE 8080
# run application with this command line
CMD ["java", "-jar", "/book-service.jar"]