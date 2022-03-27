package com.highio.shoping.controller;


import com.highio.shoping.pojo.User;
import com.highio.shoping.service.IGoodsService;
import com.highio.shoping.service.IUserService;
import com.highio.shoping.vo.GoodsVo;
import com.highio.shoping.vo.ResponseBean;
import com.highio.shoping.vo.ResponseBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class ShoppingController {

    @Autowired
    IUserService userService;

    @Autowired
    IGoodsService goodsService;


    @RequestMapping("/getList")
    public ResponseBean getList(){
//        if(userTicket == null || userTicket.equals("")){
//            return ResponseBean.error(ResponseBeanEnum.NOLOGINERROR);
//        }

//        使用springsession
//        User user = (User) session.getAttribute(userTicket);
//
//        User user = userService.getUserByTicket(userTicket,request,response);
//
//        if(user == null){
//            return ResponseBean.error(ResponseBeanEnum.UNKNOWERROR);
//        }
        List<GoodsVo> list = goodsService.getGoodsList();
        return ResponseBean.success(list);
    }

    @RequestMapping("/getDetail")
    public ResponseBean getGoodsDetail(@RequestParam BigInteger goodsId){
        GoodsVo good = goodsService.getGoodsDetails(goodsId);
        return ResponseBean.success(good);
    }




}
