FROM  adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
LABEL maintainer="romangaponov25@gmail.com"
VOLUME /tmp
EXPOSE 8080
ADD build/libs/socialNetwork-0.0.1-SNAPSHOT.jar socialNetwork-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","socialNetwork-0.0.1-SNAPSHOT.jar"]