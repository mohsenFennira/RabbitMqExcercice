package com.example.rabbitmq.consumer;

import com.example.rabbitmq.dto.User;
import com.example.rabbitmq.publisher.RabbitMQJsonProducer;
import com.example.rabbitmq.publisher.RabbitMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    @RabbitListener(queues = {"${rabbitmq.queue.name.json}"})
    public void consume(User user){
        LOGGER.info(String.format("received message -> %s",user.toString()));
    }
}
