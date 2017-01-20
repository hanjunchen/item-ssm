package com.hsgene.test2;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hjc on 2017/1/17.
 */
public class RedisTest {

    private static ApplicationContext applicationContext;
    private static RedisTemplate redisTemplate;


    @Before
    public void before(){
        applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        redisTemplate = applicationContext.getBean(RedisTemplate.class);
    }

    @Test
    public void testList(){
        String[] s = {"qwe","asd","zxc"};
        List<String> list = Arrays.asList(s);
        System.out.println(JSON.toJSONString(redisTemplate));
        redisTemplate.boundValueOps("memberIds").set(JSON.toJSONString(list));
        System.out.println(redisTemplate.boundValueOps("memberIds").get());
    }
}
