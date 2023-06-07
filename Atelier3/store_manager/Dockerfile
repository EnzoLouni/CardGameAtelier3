FROM openjdk:11
VOLUME /tmp
EXPOSE 8083
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/store_manager?currentSchema=public \
      USER_RIBBON_LISTOFSERVERS=userservice:8081 \
      CARD_RIBBON_LISTOFSERVERS=cardservice:8082 \
      POSTGRES_USER=card \
      POSTGRES_PASSWORD=card
ARG JAR_FILE=target/store_manager-1.0.0-Final.jar
ADD ${JAR_FILE} store_manager-1.0.0-Final.jar
ENTRYPOINT ["java","-jar","/store_manager-1.0.0-Final.jar"]