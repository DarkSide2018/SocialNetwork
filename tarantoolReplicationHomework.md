1. Для того чтобы собрать репликацию - нужен centos, поэтому
 Запустим centos в докере
 
 
 $ docker pull centos
 
 $ docker run -i -t centos
 
 в терминале докера 
 
 yum install git 
 
 git clone https://github.com/tarantool/mysql-tarantool-replication.git
 
 cd mysql-tarantool-replication
 git submodule update --init --recursive
 cmake .
 make
 
 