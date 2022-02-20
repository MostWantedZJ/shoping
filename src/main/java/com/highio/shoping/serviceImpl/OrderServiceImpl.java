package com.highio.shoping.serviceImpl;

import com.highio.shoping.pojo.Order;
import com.highio.shoping.mapper.OrderMapper;
import com.highio.shoping.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jianzhang
 * @since 2022-02-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
