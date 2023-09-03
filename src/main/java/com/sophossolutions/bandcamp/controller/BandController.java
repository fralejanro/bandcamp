package com.sophossolutions.bandcamp.controller;

import com.sophossolutions.bandcamp.dto.BandDTO;
import com.sophossolutions.bandcamp.model.Band;
import com.sophossolutions.bandcamp.service.BandKafkaConsumerService;
import com.sophossolutions.bandcamp.service.BandSQSService;
import com.sophossolutions.bandcamp.service.BandService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/band")
public class BandController {

    private BandService bandService;

    private BandKafkaConsumerService bandKafkaConsumerService;
    private BandSQSService bandSQSService;

    public BandController(BandService bandService, BandSQSService bandSQSService, BandKafkaConsumerService bandKafkaConsumerService){
        this.bandService = bandService;
        this.bandSQSService = bandSQSService;
        this.bandKafkaConsumerService = bandKafkaConsumerService;
    }

    @GetMapping("/{id}")
    public Mono<Band> getBandById(@PathVariable Integer id) {
        return bandService.findById(id);
    }

    @GetMapping()
    public Flux<Band> getAllBands() {
        return bandService.findAll();
    }

    @PostMapping()
    public Mono<BandDTO> createBand(@RequestBody BandDTO bandDTO) {
        return bandService.createBand(
                Band.builder()
                        .name(bandDTO.getName())
                        .yearOfCreation(bandDTO.getYearOfCreation())
                        .status(bandDTO.getStatus())
                        .build()
        );
    }

    @PostMapping("/aws/createQueue")
    public Mono<String> postCreateQueue(@RequestBody Map<String, Object> requestBody){
        return Mono.just(bandSQSService.createStandarQueue((String)requestBody.get("queueName")));
    }

    @GetMapping("/topico-kafka/{topico}")
    public Mono<String> getBandByTopicoKafka(@PathVariable String topico) {
        return Mono.just(bandKafkaConsumerService.getLastBand(topico));
    }

    @PostMapping("/aws/postMessageQueue/{queueName}")
    public Mono<String> postMessageQueue(@RequestBody Band band, @PathVariable String queueName){
        return Mono.just(bandSQSService.publishStandarQueueMessage(
                bandSQSService.getStandarQueueUrl(queueName),band));
    }
}
