package com.hsgene.test3;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hsgene.entity.Employee;
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

    //  不要使用log4j的底层logger打印日志，使用封装过的slf4j方法打印
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
        Employee employee = new Employee(){{setId("1111");setCreateDate(new Date());setJob("开发");}};
        mongoTemplate.insert(employee, "EmployeeList");
    }

    @Test
    public void testInsertBatch(){
        List<Employee> list = Lists.newArrayList();
        Employee employee1 = new Employee(){{setId("2345");setCreateDate(new Date());setJob("开发");}};
        Employee employee2 = new Employee(){{setId("4567");setCreateDate(new Date());setJob("测试");}};
        list.add(employee1);
        list.add(employee2);
        mongoTemplate.insert(list, "EmployeeList");
    }

    @Test
    public void testFindAll(){
        List<Employee> list = mongoTemplate.findAll(Employee.class, "EmployeeList");
        logger.info(JSON.toJSONString(list));
    }

    @Test
    public void testFindById() {
        Employee employee = mongoTemplate.findById("2345", Employee.class, "EmployeeList");
        logger.debug(JSON.toJSONString(employee));
    }
}
