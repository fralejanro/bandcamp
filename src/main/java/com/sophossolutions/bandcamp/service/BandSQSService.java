package com.sophossolutions.bandcamp.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.sophossolutions.bandcamp.model.Band;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BandSQSService {

    private  final AmazonSQS clientSQS;

    public BandSQSService(AmazonSQS clientSQS) {
        this.clientSQS = clientSQS;
    }

    public String createStandarQueue(String queueName){
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        return clientSQS.createQueue(createQueueRequest).getQueueUrl();
    }

    public String getStandarQueueUrl(String queueName){
         return clientSQS.getQueueUrl(queueName).getQueueUrl();
    }

    public String publishStandarQueueMessage(String urlQueue, Band band){
        Map<String, MessageAttributeValue> attributeMessage = new HashMap<>();
        Objects.requireNonNull(attributeMessage.put("id", new MessageAttributeValue()
                        .withStringValue(band.getId().toString())))
                .withDataType("Number");
        Objects.requireNonNull(attributeMessage.put("name", new MessageAttributeValue()
                        .withStringValue(band.getName())))
                .withDataType("String");
        Objects.requireNonNull(attributeMessage.put("genre", new MessageAttributeValue()
                        .withStringValue(band.getGenre().toString())))
                .withDataType("Number");
        Objects.requireNonNull(attributeMessage.put("countryOfOrigin", new MessageAttributeValue()
                        .withStringValue(band.getCountryOfOrigin().toString())))
                .withDataType("Number");
        Objects.requireNonNull(attributeMessage.put("yearOfCreation", new MessageAttributeValue()
                        .withStringValue(band.getYearOfCreation().toString())))
                .withDataType("Number");
        Objects.requireNonNull(attributeMessage.put("status", new MessageAttributeValue()
                        .withStringValue(band.getStatus().toString())))
                .withDataType("String");
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(urlQueue)
                .withMessageBody(band.getName())
                .withDelaySeconds(20)
                .withMessageAttributes(attributeMessage);
        return clientSQS.sendMessage(sendMessageRequest).getMessageId();

    }

    public void publishStandarQueueMessage(String urlQueue, List<Band> bands){
        for(Band band: bands){
            publishStandarQueueMessage(urlQueue,band);
        }
    }

    public List<Message> receiveMessageFromQueue(String urlQueue, Integer maxNumberMessages, Integer waitTimeSeconds){
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(urlQueue)
                .withMaxNumberOfMessages(maxNumberMessages)
                .withWaitTimeSeconds(waitTimeSeconds);
        return clientSQS.receiveMessage(receiveMessageRequest).getMessages();
    }
}
