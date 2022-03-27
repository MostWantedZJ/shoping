package com.highio.shoping.mapper;

import com.highio.shoping.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.highio.shoping.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jianzhang
 * @since 2022-02-20
 */

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> getGoodsList();


    GoodsVo getGoodsDetail(BigInteger goodsId);

}
