package com.hsgene.test3;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        BoundValueOperations valueOperations = redisTemplate.boundValueOps("memberIds");
        valueOperations.set(JSON.toJSONString(list));
        valueOperations.expire(1, TimeUnit.HOURS);
        System.out.println(valueOperations.get());
        /*List<String> list1 = Lists.newArrayList("abc","def","ghi");
        System.out.println(list1.get(2));
        BoundListOperations listOperations = redisTemplate.boundListOps("memberList");
        listOperations.set(1L,JSON.toJSONString(list1));
        System.out.println(listOperations.index(1L));*/
    }
}
