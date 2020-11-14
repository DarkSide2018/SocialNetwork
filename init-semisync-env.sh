#!/usr/bin/env bash

source scripts/my-functions.sh

MYSQL_VERSION="5.7.31"
PROXYSQL_VERSION="2.0.13"

echo
echo "Starting environment"
echo "===================="

echo "=========================="
echo "Creating network"
echo "----------------"
docker network create springboot-proxysql-mysql

echo "=========================="
echo "Starting mysql-master container"
echo "-------------------------------"
docker run -d \
  --name mysql-master \
  --network=springboot-proxysql-mysql \
  --restart=unless-stopped \
  --env "MYSQL_ROOT_PASSWORD=secret" \
  --env "MYSQL_DATABASE=social" \
  --env "MYSQL_USER=admin" \
  --env "MYSQL_PASSWORD=admin" \
  --publish 3340:3306 \
  --health-cmd='mysqladmin ping -u root -p$${MYSQL_ROOT_PASSWORD}' \
  --health-start-period=10s \
  mysql:${MYSQL_VERSION} \
    --server-id=1 \
    --binlog_format=row \
    --log-bin='mysql-bin-1.log' \
    --relay_log_info_repository=TABLE \
    --master-info-repository=TABLE \
    --gtid-mode=ON \
    --log-slave-updates=ON \
    --enforce-gtid-consistency

echo "=========================="
echo "Starting mysql-slave-1 container"
echo "--------------------------------"
docker run -d \
  --name mysql-slave-1 \
  --network=springboot-proxysql-mysql \
  --restart=unless-stopped \
  --env "MYSQL_ROOT_PASSWORD=secret" \
  --publish 3341:3306 \
  --health-cmd='mysqladmin ping -u root -p$${MYSQL_ROOT_PASSWORD}' \
  --health-start-period=10s \
  mysql:${MYSQL_VERSION} \
    --server-id=2 \
    --binlog_format=row \
    --enforce-gtid-consistency=ON \
    --log-slave-updates=ON \
    --read_only=TRUE \
    --skip-log-bin \
    --skip-log-slave-updates \
    --gtid-mode=ON

echo "=========================="
echo "Starting mysql-slave-2 container"
echo "--------------------------------"
docker run -d \
  --name mysql-slave-2 \
  --network=springboot-proxysql-mysql \
  --restart=unless-stopped \
  --env "MYSQL_ROOT_PASSWORD=secret" \
  --publish 3342:3306 \
  --health-cmd='mysqladmin ping -u root -p$${MYSQL_ROOT_PASSWORD}' \
  --health-start-period=10s \
  mysql:${MYSQL_VERSION} \
    --server-id=3 \
    --binlog_format=row \
    --enforce-gtid-consistency=ON \
    --log-slave-updates=ON \
    --read_only=TRUE \
    --skip-log-bin \
    --skip-log-slave-updates \
    --gtid-mode=ON

echo "=========================="
wait_for_container_log "mysql-master" "port: 3306"
wait_for_container_log "mysql-slave-1" "port: 3306"
wait_for_container_log "mysql-slave-2" "port: 3306"

echo "=========================="
echo "Setting MySQL Replication"
echo "-------------------------"
docker exec -i mysql-master mysql -uroot -psecret < scripts/semisync/master-replication.sql
docker exec -i mysql-slave-1 mysql -uroot -psecret < scripts/semisync/slave-replication.sql
docker exec -i mysql-slave-2 mysql -uroot -psecret < scripts/semisync/slave-replication.sql

echo "=========================="
echo "Checking MySQL Replication"
echo "--------------------------"
./scripts/check-replication-status.sh

echo "=========================="
echo "Creating ProxySQL monitor user"
echo "------------------------------"
docker exec -i mysql-master mysql -uroot -psecret --ssl-mode=DISABLED < scripts/master-proxysql-monitor-user.sql

echo
echo "Waiting 5 seconds before starting proxysql container ..."
sleep 5

echo "=========================="
echo "Starting proxysql container"
echo "---------------------------"
docker run -d \
  --name proxysql \
  --hostname=proxysql\
  --network=springboot-proxysql-mysql \
  --restart=unless-stopped \
  --publish 6032:6032 \
  --publish 6033:6033 \
  --volume $PWD/scripts/proxysql.cnf:/etc/proxysql.cnf \
    proxysql/proxysql:${PROXYSQL_VERSION}

echo "=========================="
echo "Waiting 5 seconds before checking mysql servers"
sleep 5

echo "=========================="
echo "Checking mysql servers"
echo "----------------------"
docker exec -i mysql-master bash -c 'mysql -hproxysql -P6032 -uradmin -pradmin --prompt "ProxySQL Admin> " <<< "select * from mysql_servers;"'

echo "=========================="
echo "Environment Up and Running"
echo "=========================="
