package com.hsgene.test1;

import com.hsgene.utils.Pinyin4jUtils;
import org.junit.Test;

/**
 * Created by hjc on 2016/11/11.
 */
public class PinYinTest {

    @Test
    public void testPinYin(){
        System.out.println(Pinyin4jUtils.chineseToSpell("中国"));
        System.out.println(Pinyin4jUtils.chineseToFirstSpell("呵呵哒"));
    }
}
