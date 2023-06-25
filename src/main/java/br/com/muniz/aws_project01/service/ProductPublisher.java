package br.com.muniz.aws_project01.service;

import br.com.muniz.aws_project01.enums.EventTypeEnumm;
import br.com.muniz.aws_project01.model.Envelope;
import br.com.muniz.aws_project01.model.Product;
import br.com.muniz.aws_project01.model.ProductEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductPublisher {

    private final AmazonSNS snsClient;
    private final Topic producteventstopic;
    private final ObjectMapper objectMapper;

    public ProductPublisher(AmazonSNS snsClient,
                            @Qualifier("productEventsTopic") Topic productEventsTopic,
                            ObjectMapper objectMapper) {
        this.snsClient = snsClient;
        this.producteventstopic = productEventsTopic;
        this.objectMapper = objectMapper;
    }

    public void publishProductEvent(Product product, EventTypeEnumm eventType, String username) {
        ProductEvent productEvent = new ProductEvent();
        productEvent.setProductId(product.getId());
        productEvent.setCode(product.getCode());
        productEvent.setUsername(username);

        Envelope envelope = new Envelope();
        envelope.setEventType(eventType);

        try {
            envelope.setData(objectMapper.writeValueAsString(productEvent));
            PublishResult publishResult = snsClient.publish(
                    producteventstopic.getTopicArn(),
                    objectMapper.writeValueAsString(envelope)
            );
            log.info("Product Event Sent. Event: {}, ProductID: {}, MessageID: {}",
                    envelope.getEventType(),
                    productEvent.getProductId(),
                    publishResult.getMessageId());
        } catch (JsonProcessingException e) {
            log.error("Erro ao criar o evento de produto,", e);
        }
    }

}
