package com.hsgene.test;

import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hjc on 2016/11/11.
 * java正则和js正则语法基本一致
 */
public class PatternTest {

    /**
     * 捕获组匹配、预测先行后行搜索/零宽断言===最大的特点就是不消费字符，只规定前或后位置字符
     */
    @Test
    public void testGroup() {
        //  ***正则中加边界^和$是为了完全匹配
        Pattern pattern = Pattern.compile("(一组)(二组)(三组)\\2+");  //  \n选取捕获组，\2反向引用
        Matcher matcher = pattern.matcher("一组二组三组二组");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        Pattern pattern2 = Pattern.compile("(一组)(?:二组)(三组)\\2+");   //  ?:使该组()不作为捕获组
        Matcher matcher2 = pattern2.matcher("一组二组三组三组组");
        while (matcher2.find()) {
            System.out.println(matcher2.group());
        }
        Pattern pattern3 = Pattern.compile("起始点字符(?=a|b|c)");   //  ?=匹配该位置但不消费字符，不作为捕获组，与?<=相反（?=规定字符串后的位置必须是xxx，?<=规定字符串前的位置必须是xxx）
        Matcher matcher3 = pattern3.matcher("起始点字符b");
        while (matcher3.find()) {
            System.out.println(matcher3.group());
        }
        Pattern pattern4 = Pattern.compile("起始点字符(?!a|b|c)a+");   //  ?!该位置不匹配不消费字符，后断，不作为捕获组，与?<!相反，前断
        Matcher matcher4 = pattern4.matcher("起始点字符d");   //  要求(?!a|b|c)位置必须是
        while (matcher4.find()) {
            System.out.println(matcher4.group());
        }
        //  其中负向零宽断言?!（后断，?<!是后断）实用性很大，经常会用到，经常用于不包含某些字符的情况
        //  [^a]与(?!a)的区别：前者会匹配到一个字符，后者只匹配一个位置，不消费字符，规定该位置不能是a才会与其匹配
       //  死方法：当复杂正则难以写出时，可以将一个正则分解成多个整个，分别进行判断即可
        Pattern pattern5 = Pattern.compile("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$");  // 两个前断规定了从[0-9A-Za-z]{6,10}$前面的位置直到$末尾都不能是全数字和全字母
        Matcher matcher5 = pattern5.matcher("123aaa11");
        while (matcher5.find()) {
            System.out.println(matcher5.group());
        }
        //  详细教程：http://deerchao.net/tutorials/regex/regex.htm#negativelookaround
        Pattern pattern6 = Pattern.compile("(?<=<(\\w+)>).*(?=</\\1>)");
        Matcher matcher6 = pattern6.matcher("<span>呵呵哒</span>");
        while (matcher6.find()) {
            System.out.println(matcher6.group());
        }
    }

    @Test
    public void testString() {
        Pattern pattern = Pattern.compile("\"[a-zA-Z]+\":\"[^\"]*分子检测");
        Matcher matcher = pattern.matcher("{\"summary\":\"【附件】\",\"moleIns\":\"【分子检测】\",\"chrt\":\"【化疗】\",");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        Pattern pattern2 = Pattern.compile("patient_[^b]+");
        Matcher matcher2 = pattern2.matcher("patient_basic");
        while (matcher2.find()) {
            System.out.println(matcher2.group());
        }
        Pattern pattern3 = Pattern.compile("(medical_targetedTherapy)|(medical_medicine)|(medical_method)");
        Matcher matcher3 = pattern3.matcher("medical_medicine");
        while (matcher3.find()) {
            System.out.println(matcher3.group());
        }
    }

    @Test
    public void testDate() {
        //  匹配 2015-09-11 格式的日期，注意匹配到空白字符后得到的group()是不包含空白字符的
        String regex = "入院时间.\\s*\\d{4}(-|年)((0?[1-9])|(1[012]))(-|月)((0?[1-9])|([12]\\d)|(3[01]))日?((([01]\\d)|(2[0-3])):\\w{2})?(\\s|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("姓名：孔昭胜               入院日期：2015-10-07");
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group().length());
        }
    }

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
