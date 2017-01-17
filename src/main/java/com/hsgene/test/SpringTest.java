package com.hsgene.test;

import com.hsgene.entity.Employee;
import com.hsgene.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by hjc on 2016/10/15.
 */
public class SpringTest {

    private SqlSessionTemplate sqlSessionTemplate;

    protected ApplicationContext applicationContext;

    private EmployeeService employeeService;

    @Before
    public void setup() {
        //  获取spring容器上下文，一般这种方式用于测试时手动获取上下文，而当spring容器已经启动了就不推荐再使用这种方式获取，否则每次都要初始化相当于重启一次服务器，而是使用实现ApplicationContextAware接口的方式，由spring容器自己获取上下文
        applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        //  SqlSessionTemplate存在于spring中，而spring中的SqlSessionTemplate已被注入了相应的属性，
        //  所以只有从spring中拿到的SqlSessionTemplate才是我们需要的
        //  每次访问数据库都会实例化一个SqlSessionTemplate
        sqlSessionTemplate = applicationContext.getBean(SqlSessionTemplate.class);
        employeeService = (EmployeeService) applicationContext.getBean("employeeService");
    }

    @Test
    public void testFindList() {
        List<Employee> list = sqlSessionTemplate.selectList("com.hsgene.dao.EmployeeDao.findList");
        list.forEach(x -> System.out.println(x.getName()));
    }

    @Test
    public void testGetById() {
        Employee employee = employeeService.getById(String.valueOf(1000));
        System.out.println(employee.getName());
    }

    @Test
    public void insert(){
        Employee employee = new Employee("小小", 1, "进货员", 3, "123456789012345678", 10, new Date());
        employee.setId(2000);
        employeeService.insert(employee);
        System.out.println(employee.getId());
    }

}
