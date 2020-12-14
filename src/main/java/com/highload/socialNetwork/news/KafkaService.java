package com.highload.socialNetwork.news;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

@Component
public class KafkaService {
    private Flux<ReceiverRecord<String, String>> testTopicStream;

    public KafkaService() throws IOException {
        Properties kafkaProperties = PropertiesLoaderUtils.loadAllProperties("ccloud.properties");
        kafkaProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "reactive-consumer");
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create(kafkaProperties);
        testTopicStream = createTopicCache(receiverOptions, "testTopic");
    }


    public Flux<ReceiverRecord<String, String>> getTestTopicFlux() {
        return testTopicStream;
    }

    private <T, G> Flux<ReceiverRecord<T, G>> createTopicCache(ReceiverOptions<T, G> receiverOptions, String topicName) {
        ReceiverOptions<T, G> options = receiverOptions.subscription(Collections.singleton(topicName));
        return KafkaReceiver.create(options).receive().cache();
    }
}
