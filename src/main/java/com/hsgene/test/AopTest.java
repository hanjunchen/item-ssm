package com.hsgene.test;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by hjc on 2016/12/8.
 * 定义一个切面
 * 由切入点Pointcut和通知advice组成(增强操作)
 */

@EnableAspectJAutoProxy(proxyTargetClass = true)  // 等同于xml中配置aop:aspectj-autoproxy
@Component
@Aspect
public class AopTest {

    private Logger logger = Logger.getLogger(AopTest.class);

    /**
     * 定义切入点和切入点正则
     * execution(* com..*.*(..))第一个*表示权限符号，后面跟包名加方法名、..*表示包及其所有子包、*(..)表示任意方法任意个参数
     */
    @Pointcut("execution(* com..*.service.*.find*(..))")
    private void aspect(){
    }

    /**
     * 前置通知
     * @param joinPoint 不是必需的，但是不能是ProceedingJoinPoint对象，只能是其接口对象JoinPoint，ProceedingJoinPoint对象只支持Around advice
     */
    @Before("aspect()")
    public void before(JoinPoint joinPoint){
        logger.debug("环绕通知==切入类型：" + joinPoint.getKind());
        logger.debug("前置通知==对象：" + joinPoint.getTarget().getClass().getTypeName());
    }

    /**
     * 环绕通知
     * @param joinPoint 必有第一个参数ProceedingJoinPoint
     * @return
     */
    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object o = null;
        try {
            logger.debug("环绕通知==方法：" + joinPoint.getSignature().getName());
            o = joinPoint.proceed();
            logger.debug("环绕通知==方法参数：" + JSON.toJSONString(joinPoint.getArgs()));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return o;
    }

    /**
     * 后置通知
     * @param returnValue 被切入方法返回值，方法无返回值其为null
     */
    @AfterReturning(pointcut = "aspect()",returning = "returnValue")
    public void afterReturn(Object returnValue){
        logger.debug("后置通知==被切入方法返回值：" + JSON.toJSONString(returnValue));
    }

    /**
     * 最终通知
     */
    @After("aspect()")
    public void after(){
        logger.debug("最终通知==一般用于清空关闭资源");
    }

    /**
     * 异常通知
     * 被切入方法抛出异常时执行
     * @param e
     */
    @AfterThrowing(pointcut = "aspect()",throwing = "e")
    public void afterThrowing(Exception e){
        logger.debug("异常通知==" + e.getMessage());
    }

    //  除了可以异常通知，也可以使用aspect进入日志切入
}
