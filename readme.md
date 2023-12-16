Rabbit MQ ->
docker run -it --rm --name rabbitmq -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management
localhost:15672 - для входа в консоль
Для работы с Rabbit MQ нужно в консоле rabbitmq создать обменник, создать очередь и связать их между собой.


kafka ->
https://kafka.apache.org/quickstart
1. Скачиваем кафку (в нем полно sh которые будем поочередно запускать)
2. запускаем зукипер bin/zookeeper-server-start.sh config/zookeeper.properties 
3. запускаем кафка bin/kafka-server-start.sh config/server.properties
4. создаем топик bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
Для получении информации о топике можно использовать bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092

Защита ендпоинтов **актуатора** делается аналогично любым другим ендпоинтам. 
Но best practise - это не использовать захардкоженный через ямл файл путь, а использовать **EndpointRequest.toAnyEndpoint()** - метод специально для актуатора, который обезопсит ендпоинты актуатора не зависимо от base-path в ямле
Можно использовать с exclude **EndpointRequest.toAnyEndpoint().excluding("health", "info")**
Или же выбрать те ендпоинты, которые нужно закрыть авторизацией - http.requestMatcher(EndpointRequest.to("beans", "threaddump", "loggers"))
