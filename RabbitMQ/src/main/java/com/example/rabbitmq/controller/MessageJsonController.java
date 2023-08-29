package com.example.rabbitmq.controller;

import com.example.rabbitmq.dto.User;
import com.example.rabbitmq.publisher.RabbitMQJsonProducer;
import com.example.rabbitmq.publisher.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
    @Autowired
    private RabbitMQJsonProducer rm;

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
      rm.sendJsonMessage(user);
      return ResponseEntity.ok("json message sent to RabbitMa");
    }
}
