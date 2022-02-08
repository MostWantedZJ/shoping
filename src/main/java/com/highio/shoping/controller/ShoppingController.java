package com.highio.shoping.controller;


import com.highio.shoping.pojo.User;
import com.highio.shoping.vo.ResponseBean;
import com.highio.shoping.vo.ResponseBeanEnum;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/goods")
public class ShoppingController {

    @RequestMapping("/getList")
    public ResponseBean getList(HttpSession session, @CookieValue("userTicket")String userTicket){
        if(userTicket == null || userTicket.equals("")){
            return ResponseBean.error(ResponseBeanEnum.NOLOGINERROR);
        }
        User user = (User) session.getAttribute(userTicket);
        if(user == null){
            return ResponseBean.error(ResponseBeanEnum.UNKNOWERROR);
        }



    }


}
