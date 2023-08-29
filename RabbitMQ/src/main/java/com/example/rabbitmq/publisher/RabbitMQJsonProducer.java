package com.example.rabbitmq.publisher;

import com.example.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.jsonroutingkey.name}")
    private String routingJsonkey;
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQProducer.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(User user){
        LOGGER.info(String.format("json message sent -> %s",user.toString()));
        rabbitTemplate.convertAndSend(exchange,routingJsonkey,user);
    }
}
