/*package org.babel.consumers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    public static void main(final String[] args) throws Exception {
        final String topic = "test-topic";

        String configFile = "./config/default.properties";
        System.out.println("Default config file "+configFile);
        if (args.length > 0){
            configFile = args[0];
            System.out.println("Customized config file "+configFile);
        }

        final Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // Define grupo y offset
        final String consumerGroup = MethodHandles.lookup().lookupClass()+"Group";
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        try (final org.apache.kafka.clients.consumer.Consumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList(topic));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    String key = record.key();
                    String value = record.value();
                    System.out.println(
                            String.format("Consumed event from topic %s: key = %-10s value = %s", topic, key, value));
                }
            }
        }
    }
}*/
