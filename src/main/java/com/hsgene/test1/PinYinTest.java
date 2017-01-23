package com.hsgene.test1;

import com.hsgene.utils.Pinyin4jUtil;
import org.junit.Test;

/**
 * Created by hjc on 2016/11/11.
 */
public class PinYinTest {

    @Test
    public void testPinYin(){
        System.out.println(Pinyin4jUtil.chineseToSpell("中国"));
        System.out.println(Pinyin4jUtil.chineseToFirstSpell("呵呵哒"));
    }
}
