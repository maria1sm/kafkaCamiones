 ```bash
  docker compose -f ./docker/compose-kraft.yml updocker compose -f compose-kraft.yml up -d
  ```

 ```bash
  docker exec -it broker bash
  ```

### Cmd 1
 ```bash
  kafka-topics --create --bootstrap-server localhost:9092 
  --replication-factor 1 --partitions 2 --topic camiones-topic
  ``` 

### Cmd 2
```bash
  kafka-console-consumer --bootstrap-server localhost:9092 
  --topic topic-key --from-beginning --partition 0 
  --property "print.key=true"
  ```

### Cmd 3
```bash
  kafka-console-consumer --bootstrap-server localhost:9092 
  --topic topic-key --from-beginning --partition 1 
  --property "print.key=true"
  ```