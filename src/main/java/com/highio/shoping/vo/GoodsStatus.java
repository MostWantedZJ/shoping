package com.highio.shoping.vo;


import lombok.*;


@Getter
@ToString
@AllArgsConstructor
public enum GoodsStatus {

    NOPAYING(1,"未付款"),
    PAYED(2,"已付款"),
    FINISHED(3,"交易完成"),
    CLOSED(4,"交易关闭")
    ;

    private final Integer code;
    private final String message;

}
