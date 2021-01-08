#!/usr/bin/env bash

source scripts/my-functions.sh

sh init-async-env.sh
echo
echo
echo "build and run spring boot jar"
echo "=========================="
echo
./gradlew clean build
docker build . -t spring-social
docker run -d -p 8080:8080 --network=springboot-proxysql-mysql --name springSocial spring-social

cd reactNewsApp/react-news

docker build -t social-client .
docker run -d -p 3000:3000 --network=springboot-proxysql-mysql --name socialClient social-client

echo
echo "spring social was started"
echo "=========================="
echo

