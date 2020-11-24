#!/usr/bin/env bash

echo
echo "Starting the environment shutdown"
echo "================================="

echo
echo "Removing containers"
echo "-------------------"
docker rm -fv mysql-master mysql-slave-1 mysql-slave-2 proxysql spring-social
docker rm -fv mysql-master-2 mysql-slave-10 mysql-slave-20 proxysql-2

echo
echo "Removing network"
echo "----------------"
docker network rm springboot-proxysql-mysql
docker network rm springboot-proxysql-mysql-2

echo
echo "Environment shutdown successfully"
echo "================================="
echo