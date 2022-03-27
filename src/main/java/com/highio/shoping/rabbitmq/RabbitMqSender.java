package com.highio.shoping.rabbitmq;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToSecKill(String message){
        rabbitTemplate.convertAndSend("exchange1","seckill.order",message);
    }

}
