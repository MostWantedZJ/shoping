package com.highio.shoping.service;

import com.highio.shoping.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.highio.shoping.vo.LoginVo;
import com.highio.shoping.vo.ResponseBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jianzhang
 * @since 2022-02-06
 */
public interface IUserService extends IService<User> {

    public ResponseBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
}
