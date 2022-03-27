package com.highio.shoping.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.highio.shoping.pojo.Order;
import com.highio.shoping.pojo.SeckillGoods;
import com.highio.shoping.pojo.SeckillOrder;
import com.highio.shoping.pojo.User;
import com.highio.shoping.rabbitmq.RabbitMqSender;
import com.highio.shoping.service.IOrderService;
import com.highio.shoping.service.ISeckillGoodsService;
import com.highio.shoping.service.ISeckillOrderService;
import com.highio.shoping.service.IUserService;
import com.highio.shoping.utils.CookieUtil;
import com.highio.shoping.vo.GoodsVo;
import com.highio.shoping.vo.ResponseBean;
import com.highio.shoping.vo.ResponseBeanEnum;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jianzhang
 * @since 2022-02-21
 */
@RestController
@RequestMapping("/seckill")
public class SeckillGoodsController implements InitializingBean {
    @Autowired
    IUserService userService;

    @Autowired
    ISeckillGoodsService seckillGoodsService;

    @Autowired
    ISeckillOrderService seckillOrderService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IOrderService orderService;

    @Autowired
    RabbitMqSender rabbitMqSender;


    @RequestMapping("/doSeckill")
    public ResponseBean doSeckKill(HttpServletRequest request, @RequestParam Long goodsId,HttpServletResponse response){
        String ticket = CookieUtil.getCookieValue(request,"userTicket");
        User user = userService.getUserByTicket(ticket,request,response);
//        if(user == null){
//            return ResponseBean.error(ResponseBeanEnum.NOLOGINERROR);
//        }



        Integer stockCount = seckillGoodsService.getStockCountByIdFromRedis(goodsId);
        if(stockCount < 1){
            return ResponseBean.error(ResponseBeanEnum.NOSTOCKERROR);
        }else{
            redisTemplate.opsForValue().decrement("secgoods_id:"+goodsId);
        }



        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("user:"+user.getId()+"goods:"+goodsId);
        if(seckillOrder != null){
            return ResponseBean.error(ResponseBeanEnum.ONEBUYONCE);
        }


//        Order order = orderService.secKill(user,goodsId);
//      传给消息队列

        SeckillOrder order = new SeckillOrder();
        order.setGoodsId(goodsId);
        order.setUserId(user.getId());
        rabbitMqSender.sendToSecKill(JSON.toJSONString(order));
        return ResponseBean.success(ResponseBeanEnum.WAITING);

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        List<SeckillGoods> list =  seckillGoodsService.list();
        for(SeckillGoods item:list){
            redisTemplate.opsForValue().set("secgoods_id:"+item.getGoodsId(),item.getStockCount());
        }
    }
}
