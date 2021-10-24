package com.coderman.codemaker.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * date: 2021/10/12
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class StringCheckUtils {
    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
