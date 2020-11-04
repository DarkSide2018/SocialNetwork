# SocialNetwork

Local launch:

1. сборка проекта ./gradlew clean build (Без этого шага докер образ не соберется)
2. cборка docker образа docker build .
3. запуск приложения docker run -p 8080:8080 {imageId}
4. приложение будет доступно по адресу http://localhost:8080

Запуск окружения с мастер - слейв репликацией

sh ./init-env.sh

Когда окружение работает можно зайти на мастер и на слейв
 (Master)
mysql -h localhost -P 3340 -u root
 (Slave-1)
mysql -h localhost -P 3341 -u root  
 (Slave-2)
mysql -h localhost -P 3342 -u root  

Остановить окружение с мастер - слейв репликацией

sh ./shutdown-env.sh 

