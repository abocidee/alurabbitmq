package com.alu.itoken.rabbitmq.service.hystrix;

import com.alu.itoken.rabbitmq.service.feign.GoodsServiceApi;
import com.alu.itoken.rabbitmq.vo.GoodsVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceHystrix implements GoodsServiceApi {
    @Override
    public List<GoodsVo> listGoodsVo() {
        return null;
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return null;
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(Long goodsId) {
        return null;
    }


}
