package com.highio.shoping.ExceptionHandler;

import com.highio.shoping.vo.ResponseBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginNeededException extends RuntimeException{
    private ResponseBeanEnum responseBeanEnum;
}
