package com.hsgene.test;

import com.hsgene.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by hjc on 2016/10/15.
 */
public class JunitTest {

    private SqlSessionTemplate sqlSessionTemplate;

    protected ApplicationContext applicationContext;

    @Before
    public void setup(){
        //  获取spring容器上下文
        applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        //  SqlSessionTemplate存在于spring中，而spring中的SqlSessionTemplate已被注入了相应的属性，
        //  所以只有从spring中拿到的SqlSessionTemplate才是我们需要的
        //  每次访问数据库都会实例化一个SqlSessionTemplate
        sqlSessionTemplate = applicationContext.getBean(SqlSessionTemplate.class);
    }

    @Test
    public void testFindList(){
        List<Employee> list = sqlSessionTemplate.selectList("com.hsgene.dao.EmployeeDao.findList");
        list.forEach(x-> System.out.println(x.getName()));
    }
}
