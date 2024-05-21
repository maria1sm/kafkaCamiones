 ```bash
  docker exec -it broker bash
  ```

 ```bash
  kafka-topics --create --bootstrap-server localhost:9092 
  --replication-factor 1 --partitions 1 --topic camiones-topic
  ``` 

```bash
  kafka-console-consumer --bootstrap-server localhost:9092 
  --topic topic-key --from-beginning --partition 0 
  --property "print.key=true"
  ```