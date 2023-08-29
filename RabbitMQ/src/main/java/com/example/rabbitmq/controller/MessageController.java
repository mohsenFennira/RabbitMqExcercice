package com.example.rabbitmq.controller;

import com.example.rabbitmq.publisher.RabbitMQProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    @Autowired
    RabbitMQProducer rmp;

    @GetMapping("publish")
    public ResponseEntity<String> sendmessage(@RequestParam String message){
        rmp.sendMessage(message);
        return  ResponseEntity.ok("message sent to rabbitMQ");
    }
}
