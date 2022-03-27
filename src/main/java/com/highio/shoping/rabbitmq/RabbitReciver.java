package com.highio.shoping.rabbitmq;


import com.alibaba.fastjson.JSON;
import com.highio.shoping.pojo.Order;
import com.highio.shoping.pojo.SeckillOrder;
import com.highio.shoping.service.IOrderService;
import com.highio.shoping.serviceImpl.SeckillOrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitReciver {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IOrderService orderService;


    @RabbitListener(queues = "queue1")
    public void seckill(String message){
        SeckillOrder order = JSON.parseObject(message,SeckillOrder.class);
        Order od = orderService.secKill(order.getUserId(),order.getGoodsId());
        redisTemplate.opsForValue().set("user:"+(order.getUserId())+"goods:"+(order.getGoodsId()),order);
    }

}
