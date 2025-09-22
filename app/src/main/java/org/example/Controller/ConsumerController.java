package org.example.Controller;

import io.prometheus.client.Counter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.w3c.dom.css.Counter;

@Service
public class ConsumerController {

    private final Counter kafkaEventsCounter;

    public ConsumerController(){
        kafkaEventsCounter = Counter.build()
                .name("kafka_events_received_total")
                .help("Total number of Kafka events received")
                .register();

    }

    @KafkaListener(topics = "testy" , groupId = "metrics-consumer-group")
    public void listen(String eventData){
        System.out.println("Received event: " + eventData);
        kafkaEventsCounter.inc();
    }

}
