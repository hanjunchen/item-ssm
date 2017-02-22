package com.hsgene.test3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hsgene.common.Constant;
import com.hsgene.entity.ResponseEntity;
import com.hsgene.model.DcwEvaluationDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        url = url + "?dcwId=111";
        ResponseEntity responseEntity = restTemplate.getForObject(url, ResponseEntity.class);
        System.out.println(JSON.toJSONString(responseEntity));
    }

    @Test
    public void testPost() {
        Constant constant = new Constant();
        String paramsJson = "[{\"dcwId\":\"123\",\"fieldKey\":\"qwe\",\"id\":\"333\"},{\"dcwId\":\"456\",\"fieldKey\":\"asd\",\"id\":\"444\"}]";
        List<DcwEvaluationDetail> list = JSON.parseObject(paramsJson,new TypeReference<List<DcwEvaluationDetail>>(){});
        ResponseEntity responseEntity = restTemplate.postForObject(url, list, ResponseEntity.class);
        System.out.println(JSON.toJSONString(responseEntity));
    }

}
