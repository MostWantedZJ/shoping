package com.highio.shoping.vo;


import com.highio.shoping.validator.IsMobile;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginVo {

    @NotNull
    @IsMobile
    private String name;

    @NotNull
    private String password;


}
