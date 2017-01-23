package com.hsgene.test1;

import org.junit.Test;

/**
 * Created by hjc on 2016/11/11.
 */
public class StringTest {

    @Test
    public void testSubString() {
        System.out.println(new StringBuilder("qwe").substring(1, 2));   //  第二个参数不能超过最大索引，否则越界异常
    }

    @Test
    public void testIndexOf(){
        System.out.println("\"qwe123\"".indexOf("\"", 1));
    }

    @Test
    public void testStringBuffer(){
        String s = "sss";
        StringBuffer sb = new StringBuffer(s);
        s = "bbb";
        System.out.println(sb);
    }
}
