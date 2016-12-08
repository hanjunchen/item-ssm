package com.hsgene.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by hjc on 2016/12/8.
 * mybatis插件
 * 专门拦截ParameterHandler、ResultSetHandler、StatementHandler、Executor四个对象中的方法
 * 拦截StatementHandler中的prepare或Executor中的query方法，在sql预编译之前操作sql
 */
//  签名指定拦截StatementHandler对象的prepare方法，方法参数是args，相当于springMVC中定义拦截器后需要在xml文件mvc:interceptors标签中指定被拦截对象
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class Pagination implements Interceptor, Serializable {

    private static final long serialVersionUID = -1635707855462899432L;
    //  log4j打印日志
    private Logger logger = Logger.getLogger(Pagination.class); // org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger();
    private int pageSize;

    public Pagination() {
    }

    /**
     * 拦截增强操作
     *
     * @param invocation 封装被拦截对象的反射信息
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //  获取被拦截对象反射
        Object obj = invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(obj);
        logger.debug("");
        Object o = invocation.proceed();
        logger.debug("");
        return o;
    }

    /**
     * 生成被拦截对象的代理对象
     * 在Configuration中初始化StatementHandler时被调用
     * @param o
     * @return
     */
    @Override
    public Object plugin(Object o) {
        // 只为签名对象生成代理类
        if (o.getClass().isAssignableFrom(StatementHandler.class)) {
            return Plugin.wrap(o, this);
        }
        return o;
    }

    /**
     * 读取mybatis-config中分页插件的property
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        this.pageSize = Integer.parseInt(properties.get("pageSize").toString());
    }
}