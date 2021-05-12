package com.alu.itoken.rabbitmq.service.feign;
import com.alu.itoken.rabbitmq.domain.OrderInfo;
import com.alu.itoken.rabbitmq.service.hystrix.GoodsServiceHystrix;
import com.alu.itoken.rabbitmq.vo.GoodsVo;
import com.alu.itoken.rabbitmq.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 秒杀服务接口
 *
 * @author noodle
 */
@FeignClient(name = "alu-framework-edu" ,fallback = GoodsServiceHystrix.class)
public interface SeckillServiceApi {
    /**
     * 创建验证码
     *
     * @param user
     * @param goodsId
     * @return
     */
   // String createVerifyCode(UserVo user, long goodsId);

    /**
     * 执行秒杀操作，包含以下两步：
     * 1. 从goods表中减库存
     * 2. 将生成的订单写入miaosha_order表中
     *
     * @param user  秒杀商品的用户
     * @param goods 所秒杀的商品
     * @return 生成的订单信息
     */
    @RequestMapping("seckill")
    OrderInfo seckill(@RequestParam("user") UserVo user, @RequestParam("goods") GoodsVo goods);

    /**
     * 获取秒杀结果
     *
     * @param userId
     * @param goodsId
     * @return
     */
   // long getSeckillResult(Long userId, long goodsId);
}
