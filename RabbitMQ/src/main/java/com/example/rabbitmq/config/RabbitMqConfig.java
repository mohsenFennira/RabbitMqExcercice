package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.queue.name.json}")
    private String jsonqueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routingkey.name}")
    private String routingkey;
    @Value("${rabbitmq.jsonroutingkey.name}")
    private String jsonroutingkey;
    @Bean
     public Queue queue(){
        return new Queue(queue);
    }
    //spring bean for queue to store json messages
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonqueue);
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(exchange);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(topicExchange())
                .with(routingkey);
    }
    @Bean
    public Binding bindingJson(){
        return BindingBuilder.bind(jsonQueue())
                .to(topicExchange())
                .with(jsonroutingkey);
    }
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
   @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
    //connectionfactory
    //RabbitTemplate
    //RabbitAdmin
    //AutoconfigurationWith SpringBoot
}
