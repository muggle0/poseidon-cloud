package com.muggle.poseidon.util;

import com.muggle.poseidon.base.exception.SimplePoseidonException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/4/27
 **/
public class DateUtils {

    /**
     * 获取日期格式
     * @param str
     * @return
     */
    private static String getDateFormat(String str) {
        boolean year = false;
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if(pattern.matcher(str.substring(0, 4)).matches()) {
            year = true;
        }
        StringBuilder sb = new StringBuilder();
        int index = 0;
        if(!year) {
            if(str.contains("月") || str.contains("-") || str.contains("/")) {
                if(Character.isDigit(str.charAt(0))) {
                    index = 1;
                }
            }else {
                index = 3;
            }
        }
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            if(Character.isDigit(chr)) {
                if(index==0) {
                    sb.append("y");
                }
                if(index==1) {
                    sb.append("M");
                }
                if(index==2) {
                    sb.append("d");
                }
                if(index==3) {
                    sb.append("H");
                }
                if(index==4) {
                    sb.append("m");
                }
                if(index==5) {
                    sb.append("s");
                }
                if(index==6) {
                    sb.append("S");
                }
            }else {
                if(i>0) {
                    char lastChar = str.charAt(i-1);
                    if(Character.isDigit(lastChar)) {
                        index++;
                    }
                }
                sb.append(chr);
            }
        }
        if (sb.toString().equals(str)){
            throw new SimplePoseidonException("日期格式错误");
        }
        return sb.toString();
    }

    /**
     * 解析日期 是否严格
     * @param dateStr
     * @param lenient
     * @return
     * @throws ParseException
     */
    public static Date paserDate(String dateStr,boolean lenient) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(getDateFormat(dateStr));
        format.setLenient(lenient);
        return format.parse(dateStr);
    }


    /**
     * date 转 localDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateToTime(Date date){

        LocalDateTime ldt = date.toInstant()
                .atZone( ZoneId.systemDefault() )
                .toLocalDateTime();
        return ldt;
    }

    /**
     * localdateTime转 date
     * @param localDateTime
     * @return
     */
    public static Date timeToDate(LocalDateTime localDateTime){
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        return date;
    }

    public static void main(String[] args) {
        String test="xxxxxx";
        System.out.println(getDateFormat(test));
    }
}
