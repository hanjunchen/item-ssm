package com.hsgene.test3;

import com.alibaba.fastjson.JSON;
import com.hsgene.common.Constant;
import com.hsgene.entity.ResponseEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hjc on 2017/2/7.
 */
@ContextConfiguration(locations = {"/spring-context.xml"})
public class RestTemplateTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private RestTemplate restTemplate;

    private static String url = Constant.getValue("tbd-api.url");

    @Test
    public void testGet() {

    }

    @Test
    public void testPost() {
        Constant constant = new Constant();
        ResponseEntity responseEntity = restTemplate.postForObject(url, null, ResponseEntity.class, "");
        System.out.println(JSON.toJSONString(responseEntity));
    }

}
