# Ping pong service

## Front

## Back

Текущие границы времени ответа GRPC-сервиса

```shell
curl http://localhost:8080/bound
```

Установка границ времени ответа

```shell
curl -d '{ "lower": 50, "upper": 200 }' http://localhost:8080/bound
```

### GRPC

Описание

```shell
grpcurl localhost:9000 list

grpcurl -plaintext localhost:9000 describe ru.sbrf.gwec.PingPong

grpcurl -plaintext localhost:9000 describe ru.sbrf.gwec.PingPong

grpcurl -plaintext localhost:9000 describe ru.sbrf.gwec.Msg
```

Запрос

```shell
grpcurl -plaintext -d '{"body": "PING"}' localhost:9000 ru.sbrf.gwec.PingPong/ping
```