package com.highio.shoping.service;

import com.highio.shoping.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.highio.shoping.vo.GoodsVo;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jianzhang
 * @since 2022-02-20
 */

public interface IGoodsService extends IService<Goods> {

    List<GoodsVo> getGoodsList();


    GoodsVo getGoodsDetails(BigInteger goodsId);
}
