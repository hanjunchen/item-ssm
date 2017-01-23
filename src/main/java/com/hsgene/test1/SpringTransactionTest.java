package com.hsgene.test1;

import com.alibaba.fastjson.JSON;
import com.hsgene.entity.Employee;
import com.hsgene.service.EmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @ContextConfiguration 注解初始化spring容器
 * @TransactionConfiguration 注解指定事务操作不回滚，默认回滚
 * spring中junit测试单元测试继承AbstractTransactionalJUnit4SpringContextTests
 * Created by hjc on 2016/12/8.
 */
@ContextConfiguration(locations = {"/spring-context.xml"})
@TransactionConfiguration(defaultRollback = false)
public class SpringTransactionTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 各类通知顺序：
     * 环绕、前置、环绕、最终、后置
     */
    @Test
    public void testAspect(){
        Employee employee = Employee.builder().name("小").build();
        JSON.toJSONString(employeeService.findList(employee));
    }
}
