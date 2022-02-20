package com.highio.shoping.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResponseBeanEnum {

    SUCCESS(200,"SUCCESS"),
    ERROR(500,"serverError"),
    LOGINERROR(50010,"loginerror"),
    VALIDERROR(50020,"validerror"),
    WRONGPASSWORDERROR(50030,"wrongpassword"),
    NOLOGINERROR(50040,"nologin"),
    UNKNOWERROR(8000,"unknow error"),
    SERVELETERRO(9000,"servlet error"),

    ;

    private final Integer code;
    private final String message;


}
