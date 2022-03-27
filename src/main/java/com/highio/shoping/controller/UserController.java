package com.highio.shoping.controller;


import com.highio.shoping.pojo.User;
import com.highio.shoping.service.IUserService;
import com.highio.shoping.serviceImpl.UserServiceImpl;
import com.highio.shoping.utils.MD5Util;
import com.highio.shoping.utils.UUIDUtil;
import com.highio.shoping.vo.LoginVo;
import com.highio.shoping.vo.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.RandomAccessFile;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/doLogin")
    public ResponseBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        log.info("{}",loginVo);
        return userService.doLogin(loginVo,request,response);
    }

    @RequestMapping("/createMapping")
    public void create() throws Exception {
        writeFile(5000);
    }

    @Transactional
    public void writeFile(int count) throws Exception {

        List<User> list = new ArrayList<User>();
        for(int i=0;i<count;i++){
            User user = new User();
            user.setId(13800000000L+i);
            user.setNickname("testuser");
            user.setPassword("251b57454f0c600aa20a51ab03756845");
            user.setSlat("123abc");
            user.setRegisterDate(new Date(20211111));
            user.setLoginCount(0);
            list.add(user);
        }


        Connection conn = getConnection();

        String sql = "INSERT INTO `t_user` (`id`, `nickname`, `password`, `slat`,`register_date`) VALUES (?,?, ?, ?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        for(int i=0;i<count;i++){
            User user = list.get(i);
            statement.setLong(1,user.getId());
            statement.setString(2,user.getNickname());
            statement.setString(3,user.getPassword());
            statement.setString(4,user.getSlat());
            statement.setDate(5,user.getRegisterDate());
            statement.addBatch();
        }
        statement.executeBatch();
        statement.clearParameters();
        conn.close();

        File file = new File("D:\\devTools\\ideaWorkSpace\\shoping\\src\\main\\resources\\userTicketMapping.txt");
        if(file.exists()){
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        raf.seek(0);

        for(int i=0;i<count;i++){
            User user = list.get(i);
            String ticket = UUIDUtil.uuid();
            redisTemplate.opsForValue().set("user:"+ticket,user);
            raf.seek(raf.length());
            raf.write((user.getId()+","+ticket).getBytes());
            raf.write("\r\n".getBytes());
        }
        raf.close();
    }

    public Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC",
                "root","123456");
    }





    public static void main(String[] args) {
        String slat = "123abc";
        String password = "123456";
        String hex = MD5Util.hexBySlat(password,slat);
        System.out.println(hex);
    }



}
