package com.alu.itoken.rabbitmq.service.feign;

import com.alu.itoken.rabbitmq.service.hystrix.RedisServiceHystrix;
import com.alu.itoken.rabbitmq.vo.KeyPrefix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ITOKEN-SERVICE-REDIS" ,fallback = RedisServiceHystrix.class,url = "localhost:8502")
public interface RedisService {

    @RequestMapping(value = "put",method = RequestMethod.POST)
    public String put(@RequestParam("key")  String key, @RequestParam("value") String value,@RequestParam("time") long time);

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public Object get(@RequestParam("key") String key);


    @RequestMapping(value = "boundHashPut",method = RequestMethod.POST)
    public String putB(@RequestParam("className") String className,@RequestParam("key") String key,@RequestParam("value") String value);

    @RequestMapping(value = "boundHashGet",method = RequestMethod.GET)
    public Object getB(@RequestParam("className") String className,@RequestParam("key") String key);

    /**
     * redis 的get操作，通过key获取存储在redis中的对象
     *
     * @param prefix key的前缀
     * @param key    业务层传入的key
     * @param clazz  存储在redis中的对象类型（redis中是以字符串存储的）
     * @return 存储于redis中的对象
     */
    @RequestMapping(value = "secGet",method = RequestMethod.GET)
    public  Object get(@RequestParam("prefix") KeyPrefix prefix, @RequestParam("prefix") String key, Class clazz);

    /**
     * redis的set操作
     *
     * @param prefix 键的前缀
     * @param key    键
     * @param value  值
     * @return 操作成功为true，否则为true
     */
    @RequestMapping(value = "SecSet" ,method = RequestMethod.POST)
    public boolean set(@RequestParam("prefix")KeyPrefix prefix, @RequestParam("key")String key,@RequestParam("value") Object value);
    /**
     * 判断key是否存在于redis中
     *
     * @param keyPrefix key的前缀
     * @param key
     * @return
     */
    @RequestMapping(value = "exist",method = RequestMethod.GET)
    public boolean exists(@RequestParam("keyPrefix")KeyPrefix keyPrefix,@RequestParam("key") String key);
    /**
     * 自增
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    @RequestMapping(value = "incr",method = RequestMethod.POST)
    public long incr(@RequestParam("keyPrefix")KeyPrefix keyPrefix,@RequestParam("key") String key);
    /**
     * 自减
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    @RequestMapping(value = "decr",method = RequestMethod.POST)
    public long decr(@RequestParam("keyPrefix")KeyPrefix keyPrefix,@RequestParam("key") String key);

    /**
     * 删除缓存中的用户数据
     *
     * @param prefix
     * @param key
     * @return
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public boolean delete(@RequestParam("prefix")KeyPrefix prefix, @RequestParam("key")String key);
}
