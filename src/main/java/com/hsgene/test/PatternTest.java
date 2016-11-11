package com.hsgene.test;

import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hjc on 2016/11/11.
 */
public class PatternTest {

    //  StringBuilder、StringBuffer、Pattern检索效率比较，性能依次递减，其中正则的效率很低
    @Test
    public void testPattern() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:/剑气洞彻九重天.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "GB2312"));   //  解决读取中文乱码问题
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String s = bufferedReader.readLine();
            if (s != null) {
                stringBuilder.append(s);
            } else {
                break;
            }
        }
        System.out.println(stringBuilder.length());

        long startTime = System.currentTimeMillis();
        System.out.println(stringBuilder.charAt(stringBuilder.indexOf("用户上传之内容结束三次")));
        long endTime = System.currentTimeMillis();
        System.out.println("StringBuilder检索用时----" + (endTime - startTime) + "ms");

        long startTime2 = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer(stringBuilder);
        System.out.println(stringBuffer.charAt(stringBuffer.indexOf("用户上传之内容结束三次")));
        long endTime2 = System.currentTimeMillis();
        System.out.println("StringBuffer检索用时----" + (endTime2 - startTime2) + "ms");

        Pattern pattern = Pattern.compile("用户上传之内容结束三次");
        Matcher matcher = pattern.matcher(stringBuilder);
        long startTime3 = System.currentTimeMillis();
        System.out.println(matcher.find());
        long endTime3 = System.currentTimeMillis();
        System.out.println("Pattern检索用时----" + (endTime3 - startTime3) + "ms");
        /*while (matcher.find()) {
            long endTime3 = System.currentTimeMillis();
            System.out.println(matcher.group());
            System.out.println("Pattern检索用时----" + (endTime3 - startTime3) + "ms");
            break;
        }*/

    }
}
