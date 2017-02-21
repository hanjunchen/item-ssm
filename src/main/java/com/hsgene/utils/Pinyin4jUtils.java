package com.hsgene.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by hjc on 2016/11/11.
 */
public class Pinyin4jUtils {

    private static final HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

    static {
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    /**
     * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（长沙市长:cssc,zssz,zssc,cssz）
     *
     * @param chinese 汉字
     * @return 拼音
     */
    public static String chineseToFirstSpell(String chinese) {
        try {
            StringBuffer pinyinName = new StringBuffer();
            for (int i = 0; i < chinese.length(); i++) {
                char c = chinese.charAt(i);
                if (c == ' ') {
                    pinyinName.append("");
                }
                //  中文
                if (c >= '\u4e00' && c <= '\u9fa5') {
                    pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat)[0].charAt(0));
                } else {
                    //不是中文
                    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                        pinyinName.append(c);
                    }
                }
            }
            return pinyinName.toString();
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        // return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
        return null;
    }

    /**
     * 汉字转换位汉语全拼，英文字符不变，特殊字符丢失
     * 支持多音字，生成方式如（重当参:zhongdangcen,zhongdangcan,chongdangcen,chongdangshen,zhongdangshen,chongdangcan）
     *
     * @param chinese 汉字
     * @return 拼音
     */
    public static String chineseToSpell(String chinese) {
        try {
            StringBuffer pinyinName = new StringBuffer();
            for (int i = 0; i < chinese.length(); i++) {
                char c = chinese.charAt(i);
                if (c == ' ') {
                    pinyinName.append("");
                }
                //  中文
                if (c >= '\u4e00' && c <= '\u9fa5') {
                    pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat)[0]);
                } else {
                    //不是中文
                    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                        pinyinName.append(c);
                    }
                }
            }
            return pinyinName.toString();
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        // return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
        return null;
    }

    /**
     * 去除多音字重复数据
     *
     * @param theStr
     * @return
     */
    private static List<Map<String, Integer>> discountTheChinese(String theStr) {
        // 去除重复拼音后的拼音列表
        List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();
        // 用于处理每个字的多音字，去掉重复
        Map<String, Integer> onlyOne = null;
        String[] firsts = theStr.split(" ");
        // 读出每个汉字的拼音
        for (String str : firsts) {
            onlyOne = new Hashtable<String, Integer>();
            String[] china = str.split(",");
            // 多音字处理
            for (String s : china) {
                Integer count = onlyOne.get(s);
                if (count == null) {
                    onlyOne.put(s, new Integer(1));
                } else {
                    onlyOne.remove(s);
                    count++;
                    onlyOne.put(s, count);
                }
            }
            mapList.add(onlyOne);
        }
        return mapList;
    }

    /**
     * 解析并组合拼音，对象合并方案(推荐使用)
     *
     * @return
     */
    private static String parseTheChineseByObject(
            List<Map<String, Integer>> list) {
        Map<String, Integer> first = null; // 用于统计每一次,集合组合数据
        // 遍历每一组集合
        for (int i = 0; i < list.size(); i++) {
            // 每一组集合与上一次组合的Map
            Map<String, Integer> temp = new Hashtable<String, Integer>();
            // 第一次循环，first为空
            if (first != null) {
                // 取出上次组合与此次集合的字符，并保存
                for (String s : first.keySet()) {
                    for (String s1 : list.get(i).keySet()) {
                        String str = s + s1;
                        temp.put(str, 1);
                    }
                }
                // 清理上一次组合数据
                if (temp != null && temp.size() > 0) {
                    first.clear();
                }
            } else {
                for (String s : list.get(i).keySet()) {
                    String str = s;
                    temp.put(str, 1);
                }
            }
            // 保存组合数据以便下次循环使用
            if (temp != null && temp.size() > 0) {
                first = temp;
            }
        }
        String returnStr = "";
        if (first != null) {
            // 遍历取出组合字符串
            for (String str : first.keySet()) {
                returnStr += (str + ",");
            }
        }
        if (returnStr.length() > 0) {
            returnStr = returnStr.substring(0, returnStr.length() - 1);
        }
        return returnStr;
    }

}