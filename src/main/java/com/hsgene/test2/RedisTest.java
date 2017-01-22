package com.hsgene.test2;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

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
        List<String> list = Lists.newArrayList(s);
//        System.out.println(JSON.toJSONString(redisTemplate));
        redisTemplate.boundValueOps("memberIds").set(JSON.toJSONString(list));
        String memberIds = (String) redisTemplate.boundValueOps("memberIds").get();
        System.out.println(JSON.parseArray(memberIds).get(0));
        redisTemplate.boundListOps("memberList").set(1L,list);
        System.out.println(redisTemplate.boundListOps("memberList").getOperations());
    }
}
