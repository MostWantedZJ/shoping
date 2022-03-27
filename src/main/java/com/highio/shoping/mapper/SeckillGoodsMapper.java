package com.highio.shoping.mapper;

import com.highio.shoping.pojo.SeckillGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.highio.shoping.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jianzhang
 * @since 2022-02-21
 */
@Mapper
public interface SeckillGoodsMapper extends BaseMapper<SeckillGoods> {

    GoodsVo findSeckillGoodsById(Long goodsId);
}
