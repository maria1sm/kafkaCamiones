package org.babel.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.babel.model.Camion;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class Producer {
    public static void main(final String[] args) throws IOException {
        // Configuración del productor
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        final String topic = "camiones-topic";

        String[] matriculas = {"E141234AAA", "E144321FFF", "E145678BBB", "E148765DDD", "E140000SSS"};

        final org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<>(props);

        //final Random rnd = new Random();
        List<Camion> items = new ArrayList<>();
        int numeroRegistrosGPS = 10;
        float min = 10;
        float max = 20;

        for (int i = 0; i < matriculas.length; i++) {
            Random r = new Random();
            float randomKm = min + r.nextFloat() * (max - min);
            items.add(new Camion(matriculas[i], randomKm, new Timestamp(new Date().getTime())));

        }

        for (int i = 0; i < numeroRegistrosGPS; i++) {
            for (Camion camion : items) {
                float addedKm = 1.3f;
                Timestamp addedTime = null;

                ObjectMapper objectMapper = new ObjectMapper();
                String itemString = objectMapper.writeValueAsString(camion);
                producer.send(
                        new ProducerRecord<>(topic, camion.getMatricula(), itemString),
                        (event, ex) -> {getFutureRecordMetadata(camion.getMatricula(), itemString, event, ex);}
                );

                camion.setKm(camion.getKm() + addedKm);
                camion.setTimestamp(new Timestamp(camion.getTimestamp().getTime() + (5 * 60 * 1000L) + (long)(Math.random() * (10 * 60 * 1000L))));

            }
        }


        producer.close();

    }

    public static void getFutureRecordMetadata(String matricula, String itemString, RecordMetadata metadata, Exception exception) {
        if (exception != null)
            exception.printStackTrace();
        else
            System.out.printf("Produced event to topic %s: user= %-10s value = %-20s partition=%d%n",
                    metadata.topic(), matricula, itemString, metadata.partition());
    }
}
