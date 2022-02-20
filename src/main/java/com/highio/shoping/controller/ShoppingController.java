package com.highio.shoping.controller;


import com.highio.shoping.pojo.User;
import com.highio.shoping.service.IUserService;
import com.highio.shoping.vo.ResponseBean;
import com.highio.shoping.vo.ResponseBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/goods")
public class ShoppingController {

    @Autowired
    IUserService userService;


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



        return ResponseBean.success();
    }


}
