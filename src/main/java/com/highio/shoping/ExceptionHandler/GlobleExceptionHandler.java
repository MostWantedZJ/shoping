package com.highio.shoping.ExceptionHandler;

import com.highio.shoping.vo.ResponseBean;
import com.highio.shoping.vo.ResponseBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;

@RestControllerAdvice
public class GlobleExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseBean handler(Exception e){
        if(e instanceof GlobleException){
            GlobleException globleException =  (GlobleException) e;
            return ResponseBean.error(globleException.getResponseBeanEnum());
        }else if(e instanceof BindException){
            BindException ex = (BindException) e;
            ResponseBean responseBean = ResponseBean.error(ResponseBeanEnum.VALIDERROR);
            responseBean.setMessage(ex.getAllErrors().get(ex.getAllErrors().size()-1).getDefaultMessage());
            return responseBean;
        }else if(e instanceof LoginNeededException){
            return ResponseBean.error(ResponseBeanEnum.SERVELETERRO);
        }
        System.out.println(e);
        return ResponseBean.error(ResponseBeanEnum.UNKNOWERROR);
    }



}
