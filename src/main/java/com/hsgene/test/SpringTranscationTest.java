package com.hsgene.test;

import com.alibaba.fastjson.JSON;
import com.hsgene.entity.Employee;
import com.hsgene.service.EmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by hjc on 2016/12/8.
 */
// 注解初始化spring容器
@ContextConfiguration(locations = {"/spring-context.xml"})
@TransactionConfiguration(defaultRollback = false)
public class SpringTranscationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testFindAop(){
        Employee employee = Employee.builder().name("小").build();
        System.out.println(employeeService);
        JSON.toJSONString(employeeService.findList(employee));
    }
}
