package com.highio.shoping.serviceImpl;

import com.highio.shoping.ExceptionHandler.GlobleException;
import com.highio.shoping.pojo.User;
import com.highio.shoping.mapper.UserMapper;
import com.highio.shoping.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.highio.shoping.utils.CookieUtil;
import com.highio.shoping.utils.MD5Util;
import com.highio.shoping.utils.UUIDUtil;
import com.highio.shoping.vo.LoginVo;
import com.highio.shoping.vo.ResponseBean;
import com.highio.shoping.vo.ResponseBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jianzhang
 * @since 2022-02-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public ResponseBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        boolean isVaild = false;
        //校验
        isVaild = true;
        User user = userMapper.selectById(loginVo.getName());
        if(user == null){
            throw new GlobleException(ResponseBeanEnum.LOGINERROR);
        }
        if(MD5Util.hexBySlat(loginVo.getPassword(),user.getSlat()).equals(user.getPassword())){
            String ticket = UUIDUtil.uuid();
            request.getSession().setAttribute(ticket,user);
            CookieUtil.setCookie(request,response,"userTicket",ticket);

            return ResponseBean.success();
        }



        throw new GlobleException(ResponseBeanEnum.WRONGPASSWORDERROR);

    }


}
