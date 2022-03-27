package com.highio.shoping.serviceImpl;

import com.highio.shoping.pojo.SeckillGoods;
import com.highio.shoping.mapper.SeckillGoodsMapper;
import com.highio.shoping.service.ISeckillGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.highio.shoping.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jianzhang
 * @since 2022-02-21
 */
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements ISeckillGoodsService {

    @Autowired
    SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public GoodsVo findSeckillGoodsById(Long goodsId) {
        return seckillGoodsMapper.findSeckillGoodsById(goodsId);
    }

    @Override
    public Integer getStockCountByIdFromRedis(Long goodsId) {
        return (Integer) redisTemplate.opsForValue().get("secgoods_id:"+goodsId);
    }
}
