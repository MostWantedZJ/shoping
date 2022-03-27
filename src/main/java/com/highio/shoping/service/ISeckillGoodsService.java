package com.highio.shoping.service;

import com.highio.shoping.pojo.SeckillGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.highio.shoping.vo.GoodsVo;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jianzhang
 * @since 2022-02-21
 */

public interface ISeckillGoodsService extends IService<SeckillGoods> {

    GoodsVo findSeckillGoodsById(Long goodsId);

    Integer getStockCountByIdFromRedis(Long goodsId);

}
