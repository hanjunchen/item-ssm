package com.hsgene.test3;

import com.google.common.collect.Lists;
import com.hsgene.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;
import java.util.List;

/**
 * Created by hjc on 2017/2/20.
 */
public class MongoTemplateTest {

    private static ApplicationContext applicationContext;
    private static MongoTemplate mongoTemplate;

    @Before
    public void before(){
        applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        mongoTemplate = applicationContext.getBean(MongoTemplate.class);
    }

    @Test
    public void testInsert(){
        Employee employee = new Employee(){{setId(1234);setCreateDate(new Date());setJob("开发");}};
        mongoTemplate.insert(employee, "EmployeeList");
    }

    @Test
    public void testInsertBatch(){
        List<Employee> list = Lists.newArrayList();
        Employee employee1 = new Employee(){{setId(2345);setCreateDate(new Date());setJob("开发");}};
        Employee employee2 = new Employee(){{setId(4567);setCreateDate(new Date());setJob("测试");}};
        list.add(employee1);
        list.add(employee2);
        mongoTemplate.insert(list, "EmployeeList");
    }
}
