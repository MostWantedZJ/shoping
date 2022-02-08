package com.highio.shoping.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean {

    private long code;
    private String message;
    private Object obj;

    public static ResponseBean success(){
        return new ResponseBean(ResponseBeanEnum.SUCCESS.getCode(), "success",null);
    }

    public static ResponseBean success(Object obj){
        return new ResponseBean(ResponseBeanEnum.SUCCESS.getCode(), "success",obj);
    }

    public static ResponseBean error(ResponseBeanEnum responseBeanEnum){
        return new ResponseBean(responseBeanEnum.getCode(),responseBeanEnum.getMessage(),null);

    }

    public static ResponseBean error(ResponseBeanEnum responseBeanEnum,Object obj){
        return new ResponseBean(responseBeanEnum.getCode(),responseBeanEnum.getMessage(),obj);
    }


}
