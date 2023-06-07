FROM openjdk:11
VOLUME /tmp
EXPOSE 8082
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/card_manager?currentSchema=public \
      USER_RIBBON_LISTOFSERVERS=userservice:8081 \
      CARD_RIBBON_LISTOFSERVERS=cardservice:8082 \
      POSTGRES_USER=card \
      POSTGRES_PASSWORD=card
ARG JAR_FILE=target/card_manager-1.0.0-Final.jar
ADD ${JAR_FILE} card_manager-1.0.0-Final.jar
ENTRYPOINT ["java","-jar","/card_manager-1.0.0-Final.jar"]