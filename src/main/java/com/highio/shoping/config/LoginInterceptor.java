package com.highio.shoping.config;

import com.highio.shoping.ExceptionHandler.LoginNeededException;
import com.highio.shoping.pojo.User;
import com.highio.shoping.service.IUserService;
import com.highio.shoping.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userTicket = null;
        User user = null;
        userTicket = CookieUtil.getCookieValue(request,"userTicket");
        user = userService.getUserByTicket(userTicket,request,response);
        if(user == null){
            throw new LoginNeededException();
        }
        return true;
    }

}
