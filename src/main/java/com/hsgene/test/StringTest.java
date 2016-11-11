package com.hsgene.test;

import org.junit.Test;

/**
 * Created by hjc on 2016/11/11.
 */
public class StringTest {

    @Test
    public void testSubString() {
        System.out.println(new StringBuilder("qwe").substring(1, 2));   //  第二个参数不能超过最大索引，否则越界异常
    }
}
