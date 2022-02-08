package com.highio.shoping.controller;


import com.highio.shoping.service.IUserService;
import com.highio.shoping.serviceImpl.UserServiceImpl;
import com.highio.shoping.utils.MD5Util;
import com.highio.shoping.vo.LoginVo;
import com.highio.shoping.vo.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jianzhang
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/doLogin")
    public ResponseBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        log.info("{}",loginVo);
        return userService.doLogin(loginVo,request,response);
    }




    public static void main(String[] args) {
        String slat = "123abc";
        String password = "123456";
        String hex = MD5Util.hexBySlat(password,slat);
        System.out.println(hex);
    }



}
