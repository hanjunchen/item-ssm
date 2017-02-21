package com.hsgene.test3;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hsgene.model.EmployeeModel;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;
import java.util.List;

/**
 * Created by hjc on 2017/2/20.
 */
public class MongoTemplateTest {

    //  不用log4j的logger打印日志，使用封装过的slf4j方法打印
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static ApplicationContext applicationContext;
    private static MongoTemplate mongoTemplate;

    @Before
    public void before(){
        applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        mongoTemplate = applicationContext.getBean(MongoTemplate.class);
    }

    @Test
    public void testInsert(){
        EmployeeModel employeeModel = new EmployeeModel(){{setId("1111");setCreateDate(new Date());setJob("开发");}};
        mongoTemplate.insert(employeeModel, "EmployeeList");
    }

    @Test
    public void testInsertBatch(){
        List<EmployeeModel> list = Lists.newArrayList();
        EmployeeModel employee1 = new EmployeeModel(){{setId("2345");setCreateDate(new Date());setJob("开发");}};
        EmployeeModel employee2 = new EmployeeModel(){{setId("4567");setCreateDate(new Date());setJob("测试");}};
        list.add(employee1);
        list.add(employee2);
        mongoTemplate.insert(list, "EmployeeList");
    }

    @Test
    public void testFindAll(){
        List<EmployeeModel> list = mongoTemplate.findAll(EmployeeModel.class, "EmployeeList");
        list.forEach(x-> System.out.println(x));
    }

    @Test
    public void testFindById() {
        EmployeeModel employeeModel = mongoTemplate.findById("2345", EmployeeModel.class, "EmployeeList");
        logger.info(JSON.toJSONString(employeeModel));
    }
}
