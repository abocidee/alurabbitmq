package com.alu.itoken.rabbitmq.service.feign;



import com.alu.itoken.rabbitmq.service.hystrix.GoodsServiceHystrix;
import com.alu.itoken.rabbitmq.vo.GoodsVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品服务接口
 *
 * @author noodle
 */
@FeignClient(name = "alu-framework-edu" ,fallback = GoodsServiceHystrix.class)
public interface GoodsServiceApi {

    /**
     * 获取商品列表
     *
     * @return
     */
    @RequestMapping(value = "goodsList", method = RequestMethod.POST)// produces表明：这个请求会返回text/html媒体类型的数据
    List<GoodsVo> listGoodsVo();

    /**
     * 通过商品的id查出商品的所有信息（包含该商品的秒杀信息）
     *
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "getDetails/{goodsId}")
    GoodsVo getGoodsVoByGoodsId(@RequestParam("goodsId") long goodsId);

    /**
     * 通过商品的id查出商品的所有信息（包含该商品的秒杀信息）
     *
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "getGoodsVo/{goodsId}")
    GoodsVo getGoodsVoByGoodsId(@RequestParam("goodsId") Long goodsId);

}
