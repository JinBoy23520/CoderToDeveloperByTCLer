package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 * <p>
 * Created by kuyu.yaojt on 16/7/20.
 */

public class TimeUtil {

    /**
     * 重一种模式,转换为另一种模式
     *
     * @param str
     * @param oldFormat 旧模式
     * @param newFormat 新模式
     * @return
     */
    public static String formatTime(@NonNull String str, @NonNull String oldFormat,
                                    @NonNull String newFormat) {
        if (str == null || oldFormat == null || newFormat == null) {
            return null;
        }
        SimpleDateFormat oldSDF = new SimpleDateFormat(oldFormat);
        SimpleDateFormat newSDF = new SimpleDateFormat(newFormat);
        String formatTime = null;
        try {
            Date oldD = oldSDF.parse(str);
            formatTime = newSDF.format(oldD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatTime;
    }

    /**
     * 获取系统当前时间
     *
     * @return curTime
     */
    public static String getCurTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String curTime = formatter.format(curDate);
        return curTime;
    }

    /**
     * 获取系统当前日期
     *
     * @return curData
     */
    public static String getCurData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String curData = formatter.format(curDate);
        return curData;
    }

    /**
     * 获取系统当前日期
     *
     * @return curData
     */
    public static String getCurData1() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String curData = formatter.format(curDate);
        return curData;
    }

    /**
     * 获取系统当前日期
     *
     * @return curData
     */
    public static String getCurData2() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String curData = formatter.format(curDate);
        return curData;
    }

    public static String getCurData3() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String curData = formatter.format(curDate);
        return curData;
    }

    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        //创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
        Date myDate = new Date();
        //创建一个Date实例
        calendar.setTime(myDate);
        //设置日历的时间，把一个新建Date实例myDate传入
        int hour = calendar.get(Calendar.HOUR);
        return hour;
    }

    public static int getCurrentMinute() {
        Calendar calendar = Calendar.getInstance();
        //创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
        Date myDate = new Date();
        //创建一个Date实例
        calendar.setTime(myDate);
        //设置日历的时间，把一个新建Date实例myDate传入
        int minute = calendar.get(Calendar.MINUTE);
        return minute;
    }

    /**
     * 获取上月开始天
     *
     * @return curData
     */
    public static String getLastMonthBeginDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(calendar.getTime());
    }

    /**
     * 获取上月结束天
     *
     * @return curData
     */
    public static String getLastMonthEndDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(calendar.getTime());
    }

    /**
     * 处理endtime(yyyy-mm-dd)，需要穿当前日期的前一天
     * 如果当前时间是1号，后台返回的月份是上个月，day直接取上个月月末
     * 如果当时时间不是1号，后台返回当前月份，day需要拿当前日期的前一天
     */
    public static String formatDayContent(String year, String month) {

        String currentDate = TimeUtil.getCurData();
        String[] time = currentDate.split("-");
        String day = time[2];
        /*展示的年月不是当年或者当月，日都需要显示那个月后一天*/
        if (time[2].equals("1")||!time[0].equals(year)||!time[1].equals(month)) {
            day = getDayByYearAndMonth(Integer.parseInt(year), Integer.parseInt(month));
        } else {
            int dayIntger = Integer.parseInt(day) - 1;
            day = String.valueOf(dayIntger);
        }
        if(day.length()==1){
            day="0"+day;
        }
        return day;
    }

    /*根据年月获取天数的最大值*/
    public static String getDayByYearAndMonth(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        //year年
        time.set(Calendar.MONTH, month - 1);
        //Calendar对象默认一月为0,month月
        String day = time.getActualMaximum(Calendar.DAY_OF_MONTH) + "";//本月份的天数
        return day;
    }
}
