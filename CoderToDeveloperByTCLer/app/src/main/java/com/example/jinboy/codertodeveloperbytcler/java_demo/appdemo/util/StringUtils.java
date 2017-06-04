package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 *
 * @version 1.0
 * @created 2012-3-21
 */
public class StringUtils {
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    /**
     *
     * @param amount
     * @return
     */
    public static String formatAmountWith2Decimal(String amount) {
        if (amount == null || "".equals(amount)) amount = "0";
        BigDecimal bd = new BigDecimal(Double.parseDouble(amount));
        String fAmount = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        return fAmount;
    }

    /**
     *
     * @param amount
     * @return
     */
    public static String formatAmountWith2DecimalToPositive(String amount) {
        if (amount.equals("--")) {
            return amount;
        } else {
            if (amount == null || "".equals(amount)) amount = "0";
            if (Double.parseDouble(amount) < 0) {
                BigDecimal bd = new BigDecimal(Double.parseDouble(amount) * (-100));
                String fAmount = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                return fAmount;
            } else {
                BigDecimal bd = new BigDecimal(Double.parseDouble(amount) * 100);
                String fAmount = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                return fAmount;
            }
        }
    }

    /**
     *
     * @param count
     * @return
     */
    public static String formatCount2Int(String count) {
        if (count == null || "".equals(count)) count = "0";
        int sCount = (int) Double.parseDouble(count);
        return String.valueOf(sCount);
    }

    /**
     * 格式化销售量，不保留小数点
     *
     * @param str
     * @return
     */
    public static String formatCount(String str) {
        if (str == null || "".equals(str)) str = "0";
        float fAmount = StringUtils.toFloat(str, 0.0f);
        String sFormat = String.format("%1$,f", fAmount);
        if (sFormat == null) sFormat = "0.00";
        if (sFormat.contains(".")) {
            String sf = sFormat.substring(0, sFormat.lastIndexOf("."));
            return sf;
        } else {
            return sFormat;
        }
    }

    /**
     * 格式化销售额，统一保留2位小数
     *
     * @param str
     * @return
     */
    public static String formatAmount(String str) {
        if (str == null || "".equals(str)) str = "0";
        BigDecimal bd = new BigDecimal(Double.parseDouble(str));
        double fAmount = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String sFormat = String.format("%1$,f", fAmount);
        if (sFormat == null) sFormat = "0.000";
        String formatAmount = sFormat.substring(0, sFormat.lastIndexOf(".") + 3);
        return formatAmount;
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将字符串转为日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 返回long类型的今天的日期
     *
     * @return
     */
    public static long getToday() {
        Calendar cal = Calendar.getInstance();
        String curDate = dateFormater2.get().format(cal.getTime());
        curDate = curDate.replace("-", "");
        return Long.parseLong(curDate);
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的电话号码
     */
    public static boolean isTel(String telNum) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(telNum);
        return m.matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 判断一个字符串是否都为数字
     *
     * @param str
     * @return
     */
    public static boolean isDigit(String str) {
        if (str == null) return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 字串转换为float
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static float toFloat(String str, float defaultValue) {
        try {
            if (str != null) {
                return Float.valueOf(str);
            } else {
                return 0f;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0f;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line;
            line = read.readLine();
            while (line != null) {
                res.append(line);
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != isr) {
                    isr.close();
                    isr.close();
                }
                if (null != read) {
                    read.close();
                    read = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
            }
        }
        return res.toString();
    }

    /**
     * 判断是否是json结构
     */
    public static boolean isJson(String value) {
        try {
            new JSONObject(value);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    /**
     * 判断 String[] 里有没有 某个 String
     *
     * @param stringArr
     * @param targetString
     * @return
     */
    public static boolean stringArrContans(String[] stringArr, String targetString) {
        if (stringArr != null && stringArr.length > 0) {
            for (int i = 0; i < stringArr.length; i++) {
                String temp = stringArr[i];
                if (temp.equals(targetString)) {
                    return true;
                }
            }
        }
        return false;
    }

}

