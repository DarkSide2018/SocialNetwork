CHANGE MASTER TO MASTER_HOST='mysql-master-2', MASTER_USER='replication', MASTER_PASSWORD='replication', MASTER_AUTO_POSITION = 1;
START SLAVE;
