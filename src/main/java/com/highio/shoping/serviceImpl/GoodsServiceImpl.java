package com.highio.shoping.serviceImpl;

import com.highio.shoping.pojo.Goods;
import com.highio.shoping.mapper.GoodsMapper;
import com.highio.shoping.service.IGoodsService;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
