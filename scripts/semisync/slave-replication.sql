CHANGE MASTER TO MASTER_HOST = 'mysql-master',  MASTER_USER = 'replication', MASTER_PASSWORD = 'replication', MASTER_AUTO_POSITION = 1;
START SLAVE;
INSTALL PLUGIN rpl_semi_sync_slave SONAME 'semisync_slave.so';
SET GLOBAL rpl_semi_sync_slave_enabled = 1;
STOP SLAVE IO_THREAD;
START SLAVE IO_THREAD;



