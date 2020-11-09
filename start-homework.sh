#!/usr/bin/env bash

source scripts/my-functions.sh

sh init-env.sh
echo
echo
echo "build and run spring boot jar"
echo "=========================="
echo
./gradlew clean build
docker build . -t spring-social
docker run -p 8080:8080 --network=springboot-proxysql-mysql spring-social:latest

echo
echo "spring social was started"
echo "=========================="
echo

