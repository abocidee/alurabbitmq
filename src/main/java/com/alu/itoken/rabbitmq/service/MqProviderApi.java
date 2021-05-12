package com.alu.itoken.rabbitmq.service;

import com.alu.itoken.rabbitmq.vo.SkMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 消息队列服务
 *
 * @author noodle
 */
public interface MqProviderApi {

    /**
     * 将用户秒杀信息投递到MQ中（使用direct模式的exchange）
     *
     * @param message
     */
    @RequestMapping(value = "sendSkMessage" ,method = RequestMethod.POST)
    void sendSkMessage(SkMessage message);
}
