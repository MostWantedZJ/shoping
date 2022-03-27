package com.highio.shoping.serviceImpl;

import com.highio.shoping.pojo.Goods;
import com.highio.shoping.mapper.GoodsMapper;
import com.highio.shoping.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.highio.shoping.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

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

    @Autowired
    GoodsMapper goodsMapper;


    @Override
    public List<GoodsVo> getGoodsList() {
        return goodsMapper.getGoodsList();
    }

    @Override
    public GoodsVo getGoodsDetails(BigInteger goodsId) {
        return goodsMapper.getGoodsDetail(goodsId);
    }
}
