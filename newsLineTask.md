1. Для реализации домашнего задания по обновляемой ленте новостей
были использованы Kafka Spring Java ReactJS

Внимание!!!!! Для успешного запуска Ленты новостей я увеличил стандартную память докера до 4 гигабайт
(В дефолтных натсройках стояло 2 гигабайта - поэтому приложение падало с ошибкой о нехватке памяти)

Local launch:

sh ./start-async-homework.sh
реализация ленты новостей будет доступна по адресу http://localhost:3000

Когда окружение работает можно зайти на мастер и на слейв
 (Master)
mysql -h localhost -P 3340 -u root
 (Slave-1)
mysql -h localhost -P 3341 -u root  
 (Slave-2)
mysql -h localhost -P 3342 -u root 

 (Master)
mysql -h localhost -P 3350 -u root
 (Slave-1)
mysql -h localhost -P 3351 -u root  
 (Slave-2)
mysql -h localhost -P 3352 -u root   

Остановить окружение с домашним заданием по ленте новостей можно будет вот этим скриптом

sh ./shutdown-env.sh 

 