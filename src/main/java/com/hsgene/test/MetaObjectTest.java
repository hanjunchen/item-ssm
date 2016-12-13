package com.hsgene.test;

import com.alibaba.fastjson.JSON;
import com.hsgene.entity.Employee;
import com.hsgene.entity.UpToken;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Date;

/**
 * Created by hjc on 2016/12/8.
 */
public class MetaObjectTest {

    @Test
    public void testMetaObj() {
        Employee employee = Employee.builder().createDate(new Date()).upToken(UpToken.builder().upToken("123456").build()).build();
        //  new Employee(){{}}语法可以代替@Builder注解
        employee = new Employee() {{
            setCreateDate(new Date());
            setUpToken(new UpToken() {{
                setUpToken("123456");
            }});
        }};
        //  mybatis反射操作对象
        MetaObject metaObject = SystemMetaObject.forObject(employee);
        System.out.println(metaObject.getSetterNames());
        System.out.println(JSONObject.wrap(metaObject.getSetterNames()).toString());
        System.out.println(metaObject.getGetterNames().toString());
        System.out.println(JSONObject.valueToString(metaObject.getGetterNames()));
        //  获取setter/getter方法操作的数据类型反射
        System.out.println(metaObject.getGetterType("createDate"));
        // SystemMetaObject对象操作反射最大特点：可以设置和获取多层嵌套对象的属性值，无需setter/getter方法，无视私有公有权限
        System.out.println(metaObject.getValue("upToken.upToken"));
        //  阿里的fastJson对象
        System.out.println(JSON.toJSONString(metaObject.getOriginalObject().getClass().getTypeName()));
        System.out.println(JSON.toJSONString(metaObject.getOriginalObject().getClass().isAssignableFrom(Employee.class)));
    }
}
