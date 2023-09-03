package com.sophossolutions.bandcamp.service;

import com.sophossolutions.bandcamp.util.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BandKafkaConsumerService {

    private final Logger LOGGER = LoggerFactory.getLogger(BandKafkaConsumerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public BandKafkaConsumerService(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public String getLastBand(String topico){
        ConsumerRecord<String,String> lastBand;
        KafkaConfig kafkaConfig = new KafkaConfig();
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        lastBand =  kafkaTemplate.receive(topico,0,2);
        String band = (String) Objects.requireNonNull(lastBand.value());
        //LOGGER.info("Ultima banda: "+ Objects.requireNonNull(band.getName()));
        return band;
    }

}
