package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


/**
 * 时间选择器
 * DatePickerDialog.java
 *
 * @version 1.0
 */
public class DateSelectDialog extends Dialog implements OnClickListener {
    private Context mContext;

    /**
     * 事件选取完毕监听
     */
    private OnDateSelectFinished onDateSelectFinished;
    /**
     * 确认
     */
    private Button mOk;
    /**
     * 返回
     */
    private Button mBack;
    /**
     * 开始、结束年份
     */
    private static int START_YEAR = 1990, END_YEAR = 2100;
    /**
     * 最大年龄
     */
    private final int LARGEST_AGE = 119;
    /**
     * 年
     */
    private WheelView mYearView;
    /**
     * 月
     */
    private WheelView mMonthView;
    /**
     * 日
     */
    private WheelView mDayView;
    /**
     * 时
     */
    private WheelView mHourView;
    /**
     * 分
     */
    private WheelView mMinuteView;
    /**
     * list列表(大月份)
     */
    private List<String> mListBig;
    /**
     * list列表(小月份)
     */
    private List<String> mListLittle;

    /* 是否只选择本年 */
    private boolean isOnlyThisYear = false;

    private  boolean isShowMinute = false;
    /* 是否只选择本月 */
    private boolean isOnlyThisMonth = false;

    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    int year;
    int month;
    int day;
    int hour;
    int minute;

    int curYear;
    int curMonth;
    int curDay;

    boolean HourMinuteVisiable;

    /**
     * 是否只是显示本年和本月的时间选择器
     *
     * @param context              context
     * @param date                 传入的时间 格式 2017-2-13
     * @param onDateSelectFinished onDateSelectFinished
     * @param isOnlyThisYear       isOnlyThisYear 是否只显示本年
     * @param isOnlyThisMonth      isOnlyThisMonth 是否只显示本月
     */
    public DateSelectDialog(Context context, String date, OnDateSelectFinished onDateSelectFinished, boolean isOnlyThisYear, boolean isOnlyThisMonth) {
        super(context, R.style.DialogTheme);
        setContentView(R.layout.date_layout);
        this.mContext = context;
        this.onDateSelectFinished = onDateSelectFinished;
        this.isOnlyThisYear = isOnlyThisYear;
        this.isOnlyThisMonth = isOnlyThisMonth;
        init(date, true);
    }

    /**
     * <br> </br>
     *
     * @param context
     */
    public DateSelectDialog(Context context, String date, OnDateSelectFinished onDateSelectFinished) {
        super(context, R.style.DialogTheme);
        setContentView(R.layout.date_layout);
        this.mContext = context;
        this.onDateSelectFinished = onDateSelectFinished;
        init(date, true);
    }

    public DateSelectDialog(Context context, String date, OnDateSelectFinished onDateSelectFinished, boolean isShowHour) {
        super(context, R.style.DialogTheme);
        setContentView(R.layout.date_layout);
        this.mContext = context;
        this.onDateSelectFinished = onDateSelectFinished;
        init(date, isShowHour);
    }

    private void init(String date, boolean isShowHour) {
        Calendar calendar = Calendar.getInstance();
        curYear = calendar.get(Calendar.YEAR);
        curMonth = calendar.get(Calendar.MONTH) + 1;
        curDay = calendar.get(Calendar.DATE);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        String[] ymd = date.split("-");
        if (ymd.length > 2) {
            curYear = Integer.parseInt(ymd[0]);
            curMonth = Integer.parseInt(ymd[1]) - 1;
            String[] dhm = ymd[2].split(" ");
            curDay = Integer.parseInt(dhm[0]);
            if (dhm != null && dhm.length > 1) {
                String[] hm = dhm[1].split(":");
                if (hm.length > 1) {
                    hour = Integer.parseInt(hm[0]);
                    minute = Integer.parseInt(hm[1]);
                }
            }
        }

        mOk = (Button) findViewById(R.id.btn_datetime_ok);
        mBack = (Button) findViewById(R.id.btn_datetime_cancel);
        mYearView = (WheelView) findViewById(R.id.year);
        mMonthView = (WheelView) findViewById(R.id.month);
        mDayView = (WheelView) findViewById(R.id.day);
        mHourView = (WheelView) findViewById(R.id.hour);
        mMinuteView = (WheelView) findViewById(R.id.minute);

        if (!isShowHour) {
            mHourView.setVisibility(View.GONE);
            mMinuteView.setVisibility(View.GONE);
        }

        if (!isShowMinute) {
            mMinuteView.setVisibility(View.GONE);
        }

        /* 年，月，日，时，分 等单位，和值同在 */
        TextView tv_yearUnit = (TextView) findViewById(R.id.tv_yearUnit);
        tv_yearUnit.setVisibility(mYearView.getVisibility());
        TextView tv_monthUnit = (TextView) findViewById(R.id.tv_monthUnit);
        tv_monthUnit.setVisibility(mMonthView.getVisibility());
        TextView tv_dayUnit = (TextView) findViewById(R.id.tv_dayUnit);
        tv_dayUnit.setVisibility(mDayView.getVisibility());
        TextView tv_hourUnit = (TextView) findViewById(R.id.tv_hourUnit);
        tv_hourUnit.setVisibility(mHourView.getVisibility());
        TextView tv_minuteUnit = (TextView) findViewById(R.id.tv_minuteUnit);
        tv_minuteUnit.setVisibility(mMinuteView.getVisibility());
        mOk.setOnClickListener(this);
        mBack.setOnClickListener(this);
        initDatePicker();
        mYearView.addChangingListener(yearWheelListener);
        mMonthView.addChangingListener(monthWheelListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_datetime_ok:
                if (onDateSelectFinished != null) {
                    int hour = mHourView.getCurrentItem();
                    int minute = mMinuteView.getCurrentItem();
                    int year = isOnlyThisYear ? Integer.parseInt(mYearView.getAdapter().getItem(0))
                            : mYearView.getCurrentItem() + START_YEAR;
                    int month = isOnlyThisMonth ? Integer.parseInt(mMonthView.getAdapter().getItem(0))
                            : mMonthView.getCurrentItem() + 1;
                    int day = mDayView.getCurrentItem() + 1;
                    String monthS = String.format("%02d", month);
                    String dayS = String.format("%02d", day);
                    String hourS = String.format("%02d", hour);
                    String minuteS = String.format("%02d", minute);
                    onDateSelectFinished.onSelectFinished(String.valueOf(year), monthS, dayS, hourS, minuteS);
                }
                this.dismiss();
                break;
            case R.id.btn_datetime_cancel:
                this.dismiss();
                break;
            default:
                break;
        }

    }

    /**
     * 弹出日期时间选择器
     */
    private void initDatePicker() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        START_YEAR = isOnlyThisYear ? year : year - LARGEST_AGE;
        END_YEAR = year;

        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] monthsBig = {"1", "3", "5", "7", "8", "10", "12"};
        String[] monthsLittle = {"4", "6", "9", "11"};

        mListBig = Arrays.asList(monthsBig);
        mListLittle = Arrays.asList(monthsLittle);

        // 年
        mYearView.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
        mYearView.setLabel("");// 添加文字
        int yearPos = isOnlyThisYear ? END_YEAR - START_YEAR : curYear != 0 ? curYear - START_YEAR : END_YEAR - START_YEAR;
        mYearView.setCurrentItem(yearPos);// 初始化时显示的数据 START_YEAR - END_YEAR
        mYearView.setCyclic(!isOnlyThisYear);// 可循环滚动

        // 月
        int startMonth = 1, endMonth = 12;
        if (isOnlyThisMonth) {
            startMonth = curMonth + 1;
            endMonth = startMonth;
        }

        //初始年份最大值应该是当年最大月
        mMonthView.setAdapter(new NumericWheelAdapter(startMonth, month + 1));
//        mMonthView.setAdapter(new NumericWheelAdapter(startMonth, endMonth));
        mMonthView.setLabel("");
        mMonthView.setCurrentItem(isOnlyThisMonth ? 0 : curMonth != 0 ? curMonth : month);
        mMonthView.setCyclic(!isOnlyThisMonth);

        // 日
        // 判断大小月及是否闰年,用来确定"日"的数据
        int deadDay = 0;
        if (mListBig.contains(String.valueOf(month + 1))) {
            deadDay = 31;
//            mDayView.setAdapter(new NumericWheelAdapter(1, isOnlyThisMonth ? day : 31));
        } else if (mListLittle.contains(String.valueOf(month + 1))) {
            deadDay = 30;
//            mDayView.setAdapter(new NumericWheelAdapter(1, isOnlyThisMonth ? day : 30));
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                deadDay = 29;
//                mDayView.setAdapter(new NumericWheelAdapter(1, isOnlyThisMonth ? day : 29));
            } else {
                deadDay = 28;
//                mDayView.setAdapter(new NumericWheelAdapter(1, isOnlyThisMonth ? day : 28));
            }
        }
        mDayView.setAdapter(new NumericWheelAdapter(1,day));
        mDayView.setLabel("");
        mDayView.setCurrentItem(curDay == 0 ? day - 1 : curDay - 1);
        mDayView.setCyclic(true);

        // 时
        mHourView.setAdapter(new NumericWheelAdapter(0, 23));
        mHourView.setLabel("");
        mHourView.setCurrentItem(hour);
        mHourView.setCyclic(true);

        // 分
        mMinuteView.setAdapter(new NumericWheelAdapter(0, 59));
        mMinuteView.setLabel("");
        mMinuteView.setCurrentItem(minute);
        mMinuteView.setCyclic(true);

        // 选择器字体的大小
        int textSize = mContext.getResources().getDimensionPixelSize(R.dimen.ymd_text_size);
        mDayView.TEXT_SIZE = textSize;
        mMonthView.TEXT_SIZE = textSize;
        mYearView.TEXT_SIZE = textSize;
        mHourView.TEXT_SIZE = textSize;
        mMinuteView.TEXT_SIZE = textSize;
    }

    /**
     * 添加对"年"监听
     */
    private OnWheelChangedListener yearWheelListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            int year_num = newValue + START_YEAR;
            if (year_num < curYear) {
                mMonthView.setAdapter(new NumericWheelAdapter(1, 12));
            } else if (year_num >= curYear) {
                mMonthView.setAdapter(new NumericWheelAdapter(1, curMonth + 1));
            }
            mMonthView.setCurrentItem(0);
            // 判断大小月及是否闰年,用来确定"日"的数据
            if (mListBig.contains(String.valueOf(mMonthView.getCurrentItem() + 1))) {
                mDayView.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (mListLittle.contains(String.valueOf(mMonthView.getCurrentItem() + 1))) {
                mDayView.setAdapter(new NumericWheelAdapter(1, 30));
            } else {
                if ((year_num % 4 == 0 && year_num % 100 != 0) || year_num % 400 == 0)
                    mDayView.setAdapter(new NumericWheelAdapter(1, 29));
                else
                    mDayView.setAdapter(new NumericWheelAdapter(1, 28));
            }
        }
    };

    /**
     * 添加对"月"监听
     */
    private OnWheelChangedListener monthWheelListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            int month_num = newValue + 1;
            if (month_num == (month + 1)){
                mDayView.setAdapter(new NumericWheelAdapter(1, day));
            }else{
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (mListBig.contains(String.valueOf(month_num))) {
                    mDayView.setAdapter(new NumericWheelAdapter(1, 31));
                } else if (mListLittle.contains(String.valueOf(month_num))) {
                    mDayView.setAdapter(new NumericWheelAdapter(1, 30));
                } else {
                    if (((mYearView.getCurrentItem() + START_YEAR) % 4 == 0 && (mYearView.getCurrentItem() + START_YEAR) % 100 != 0)
                            || (mYearView.getCurrentItem() + START_YEAR) % 400 == 0)
                        mDayView.setAdapter(new NumericWheelAdapter(1, 29));
                    else
                        mDayView.setAdapter(new NumericWheelAdapter(1, 28));
                }
            }
        }
    };

    /**
     * set监听
     *
     * @param onDateSelectFinished
     */
    public void setOnDateSelectFinished(OnDateSelectFinished onDateSelectFinished) {
        this.onDateSelectFinished = onDateSelectFinished;
    }

    /**
     * 监听接口
     * DatePickerUI.java
     *
     * @author wangj
     * @version 1.0
     * @date 2013-1-23 下午1:28:24
     */
    public interface OnDateSelectFinished {
        /**
         * 监听方法
         *
         * @param year
         * @param month
         * @param day
         * @param hour
         * @param minute
         */
        void onSelectFinished(String year, String month, String day, String hour, String minute);
    }

}
