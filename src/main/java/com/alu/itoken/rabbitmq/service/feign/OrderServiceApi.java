package com.alu.itoken.rabbitmq.service.feign;


import com.alu.itoken.rabbitmq.domain.OrderInfo;
import com.alu.itoken.rabbitmq.domain.SeckillOrder;
import com.alu.itoken.rabbitmq.service.hystrix.GoodsServiceHystrix;
import com.alu.itoken.rabbitmq.vo.GoodsVo;
import com.alu.itoken.rabbitmq.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 订单服务接口
 *
 * @author noodle
 */
@FeignClient(name = "alu-framework-edu" ,fallback = GoodsServiceHystrix.class)
public interface OrderServiceApi {
    /**
     * 通过订单id获取订单
     *
     * @param orderId
     * @return
     */
    OrderInfo getOrderById(long orderId);

    /**
     * 通过用户id与商品id从订单列表中获取订单信息，这个地方用到了唯一索引（unique index!!!!!）
     *
     * @param userId
     * @param goodsId
     * @return 秒杀订单信息
     */
    @RequestMapping("getSeckillOrder")
    SeckillOrder getSeckillOrderByUserIdAndGoodsId(@RequestParam("userId") long userId,@RequestParam("goodsId") long goodsId);

    /**
     * 创建订单
     *
     * @param user
     * @param goods
     * @return
     */
    OrderInfo createOrder(UserVo user, GoodsVo goods);
}
