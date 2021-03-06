package com.alu.itoken.rabbitmq.receiver;


import com.alu.itoken.rabbitmq.config.MQConfig;
import com.alu.itoken.rabbitmq.domain.SeckillOrder;
import com.alu.itoken.rabbitmq.service.feign.GoodsServiceApi;
import com.alu.itoken.rabbitmq.service.feign.OrderServiceApi;
import com.alu.itoken.rabbitmq.service.feign.RedisService;
import com.alu.itoken.rabbitmq.service.feign.SeckillServiceApi;
import com.alu.itoken.rabbitmq.vo.GoodsVo;
import com.alu.itoken.rabbitmq.vo.OrderKeyPrefix;
import com.alu.itoken.rabbitmq.vo.SkMessage;
import com.alu.itoken.rabbitmq.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MQ消息接收者, 消费者
 * 消费者绑定在队列监听，既可以接收到队列中的消息
 *
 * @author noodle
 */
@Service
public class MqConsumer {

    private static Logger logger = LoggerFactory.getLogger(MqConsumer.class);

    @Autowired
    GoodsServiceApi goodsService;

    @Autowired
    OrderServiceApi orderService;

    @Autowired
    SeckillServiceApi seckillService;

    @Autowired
    RedisService redisService;

    /**
     * 处理收到的秒杀成功信息（核心业务实现）
     *
     * @param message
     */
    @RabbitListener(queues = MQConfig.SECKILL_QUEUE)
    public void receiveSkInfo(SkMessage message) {
        logger.info("MQ receive a message: " + message);

        // 获取秒杀用户信息与商品id
        UserVo user = message.getUser();
        long goodsId = message.getGoodsId();

        // 获取商品的库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        Integer stockCount = goods.getStockCount();
        if (stockCount <= 0) {
            return;
        }

        // 判断是否已经秒杀到了（保证秒杀接口幂等性）
        SeckillOrder order = this.getSkOrderByUserIdAndGoodsId(user.getUuid(), goodsId);
        if (order != null) {
            return;
        }

        // 1.减库存 2.写入订单 3.写入秒杀订单
        seckillService.seckill(user, goods);
    }

    /**
     * 通过用户id与商品id从订单列表中获取订单信息，这个地方用了唯一索引（unique index!!!!!）
     * <p>
     * 优化，不同每次都去数据库中读取秒杀订单信息，而是在第一次生成秒杀订单成功后，
     * 将订单存储在redis中，再次读取订单信息的时候就直接从redis中读取
     *
     * @param userId
     * @param goodsId
     * @return 秒杀订单信息
     */
    private SeckillOrder getSkOrderByUserIdAndGoodsId(Long userId, long goodsId) {

        // 从redis中取缓存，减少数据库的访问
        SeckillOrder seckillOrder = (SeckillOrder)redisService.get(OrderKeyPrefix.SK_ORDER, ":" + userId + "_" + goodsId, SeckillOrder.class);
        if (seckillOrder != null) {
            return seckillOrder;
        }
        return orderService.getSeckillOrderByUserIdAndGoodsId(userId, goodsId);
    }
}
