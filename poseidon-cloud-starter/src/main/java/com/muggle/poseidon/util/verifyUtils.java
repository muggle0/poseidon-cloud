package com.muggle.poseidon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/4/29
 **/
public class verifyUtils {
    // TODO 校验邮箱  校验

    // 校验手机号
    public static boolean verifyPhone(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
