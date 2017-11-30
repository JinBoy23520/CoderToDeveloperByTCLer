package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.TimeUtil;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.ToastUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * *************************************************************
 * <p>
 * .=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.       *
 * |                     ______                     |      *
 * |                  .-"      "-.                  |      *
 * |                 /            \                 |      *
 * |     _          |              |          _     |      *
 * |    ( \         |,  .-.  .-.  ,|         / )    |      *
 * |     > "=._     | )(__/  \__)( |     _.=" <     |      *
 * |    (_/"=._"=._ |/     /\     \| _.="_.="\_)    |      *
 * |           "=._"(_     ^^     _)"_.="           |      *
 * |               "=\__|IIIIII|__/="               |      *
 * |              _.="| \IIIIII/ |"=._              |      *
 * |    _     _.="_.="\          /"=._"=._     _    |      *
 * |   ( \_.="_.="     `--------`     "=._"=._/ )   |      *
 * |    > _.="                            "=._ <    |      *
 * |   (_/                                    \_)   |      *
 * |                                                |      *
 * '-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-='      *
 * <p>
 * *************************************************************
 * <p>Created by 坚果-王健(wangjian3@kuyumall.com) on 2017/5/22.
 */

public class DoubleTimeSelectDialog extends Dialog implements View.OnClickListener{

    private Context mContext;

    /**
     * 事件选取完毕监听
     */
    private OnDateSelectFinished onDateSelectFinished;
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

    private boolean isShowMinute = false;
    /* 是否只选择本月 */
    private boolean isOnlyThisMonth = false;

    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private int curYear;
    private int curMonth;
    private int curDay;

    /**时间容器*/
    private LinearLayout mTimeContainerLl;
    /**开始时间*/
    private TextView mBeginTimeTv;
    /**结束时间*/
    private TextView mEndTimeTv;

    /**选择的开始时间*/
    private String mSelectStartTime;
    /**选择的结束时间*/
    private String mSelectEndTime;

    /**当前选择时间模式*/
    private TIME_TYPE mTimeType = TIME_TYPE.TYPE_START;

    /**最小时间*/
    private String allowedSmallestTime;
/**最大时间*/
private String allowedBiggestTime;
    private enum TIME_TYPE {
        TYPE_START,
        TYPE_END
    }



    public DoubleTimeSelectDialog(Context context) {
        super(context, R.style.PopBottomDialogStyle);
    }

    public DoubleTimeSelectDialog(Context context, String curTime, String startTime) {
        super(context, R.style.PopBottomDialogStyle);
        this.mContext = context;
        this.allowedSmallestTime = startTime;
        setContentView(R.layout.popwindow_bottom_layout);
        setCanceledOnTouchOutside(true);
        Window mDialogWindow = getWindow();
        mDialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = mDialogWindow.getAttributes();
        lp.y = 0;//设置Dialog距离底部的距离
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialogWindow.setAttributes(lp);

        initDialogView();

        init(curTime, false);

        String monthS = String.format("%02d", curMonth);
        String dayS = String.format("%02d", curDay);
        String yearS = String.format("%02d", curYear);
        if(!TextUtils.isEmpty(curTime)){
            mSelectStartTime = curTime;
            mBeginTimeTv.setText(makeFormatContent(mContext.getString(R.string.begin_at), curTime.replaceAll("-",".")));
            mSelectEndTime = curTime;
            mEndTimeTv.setText(makeFormatContent(mContext.getString(R.string.end_at), curTime.replaceAll("-",".")));
        }else{
            mSelectStartTime = yearS + "-" + monthS + "-" + dayS;
            mBeginTimeTv.setText(makeFormatContent(mContext.getString(R.string.begin_at), yearS + "." + monthS + "." + dayS));
            mSelectEndTime = yearS + "-" + monthS + "-" + dayS;
            mEndTimeTv.setText(makeFormatContent(mContext.getString(R.string.end_at), yearS + "." + monthS + "." + dayS));
        }
    }

    public DoubleTimeSelectDialog(Context context, String earliestTime, String startTime, String endTime) {
        super(context, R.style.PopBottomDialogStyle);
        this.mContext = context;
        this.allowedSmallestTime = earliestTime;
        this.allowedBiggestTime = endTime;
        setContentView(R.layout.popwindow_bottom_layout);
        setCanceledOnTouchOutside(true);
        Window mDialogWindow = getWindow();
        mDialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = mDialogWindow.getAttributes();
        lp.y = 0;//设置Dialog距离底部的距离
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialogWindow.setAttributes(lp);

        initDialogView();

        init(startTime, false);

        String monthS = String.format("%02d", curMonth);
        String dayS = String.format("%02d", curDay);
        String yearS = String.format("%02d", curYear);
        if(!TextUtils.isEmpty(startTime)){
            mSelectStartTime = startTime;
            mBeginTimeTv.setText(makeFormatContent(mContext.getString(R.string.begin_at), startTime.replaceAll("-",".")));
            mSelectEndTime = endTime;
            mEndTimeTv.setText(makeFormatContent(mContext.getString(R.string.end_at), endTime.replaceAll("-",".")));
        }else{
            mSelectStartTime = yearS + "-" + monthS + "-" + dayS;
            mBeginTimeTv.setText(makeFormatContent(mContext.getString(R.string.begin_at), yearS + "." + monthS + "." + dayS));
            mSelectEndTime = yearS + "-" + monthS + "-" + dayS;
            mEndTimeTv.setText(makeFormatContent(mContext.getString(R.string.end_at), yearS + "." + monthS + "." + dayS));
        }
    }

    private void initDialogView () {

        mTimeContainerLl = (LinearLayout) findViewById(R.id.ll_tclTimeToTime);
        mBeginTimeTv = (TextView) findViewById(R.id.tv_tclBeginTime);
        mEndTimeTv = (TextView) findViewById(R.id.tv_tclEndTime);

        findViewById(R.id.tv_tclCancel).setOnClickListener(this);
        findViewById(R.id.tv_tclOk).setOnClickListener(this);
        mBeginTimeTv.setOnClickListener(this);
        mEndTimeTv.setOnClickListener(this);

    }

    /*恢复起始时间按钮的点击状态*/
    public void recoverButtonState(){
        mTimeType = TIME_TYPE.TYPE_START;
        init(mSelectStartTime, false);
        mTimeContainerLl.setBackgroundResource(R.mipmap.begin_time_bg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tclBeginTime:
                mTimeType = TIME_TYPE.TYPE_START;
                init(mSelectStartTime, false);
                mTimeContainerLl.setBackgroundResource(R.mipmap.begin_time_bg);
                break;
            case R.id.tv_tclEndTime:
                mTimeType = TIME_TYPE.TYPE_END;
                init(mSelectEndTime, false);
                mTimeContainerLl.setBackgroundResource(R.mipmap.end_time_bg);
                break;
            case R.id.tv_tclCancel:
                this.dismiss();

                break;
            case R.id.tv_tclOk:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                try {
                    if (sdf.parse(mSelectStartTime).getTime() > sdf.parse(mSelectEndTime).getTime()) {
                        ToastUtils.showText(mContext, R.string.time_start_larger_end_not_allowed, ToastUtils.ONE_SECOND);
                    } else {
                        if (onDateSelectFinished != null) {
                            onDateSelectFinished.onSelectFinished(mSelectStartTime, mSelectEndTime);
                        }
                        this.dismiss();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

        }
    }

    public void init(String date, boolean isShowHour) {
        Calendar calendar = Calendar.getInstance();
        curYear = calendar.get(Calendar.YEAR);
        curMonth = calendar.get(Calendar.MONTH) + 1;
        curDay = calendar.get(Calendar.DATE);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        calendar.clear();

        if  (date != null) {
            String[] ymd = date.split("-");
            if (ymd.length > 2) {
                curYear = Integer.parseInt(ymd[0]);
                curMonth = Integer.parseInt(ymd[1]) - 1;
                String[] dhm = ymd[2].split(" ");
                curDay = Integer.parseInt(dhm[0]);
                if (dhm.length > 1) {
                    String[] hm = dhm[1].split(":");
                    if (hm.length > 1) {
                        hour = Integer.parseInt(hm[0]);
                        minute = Integer.parseInt(hm[1]);
                    }
                }
            }
        }

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
        findViewById(R.id.tv_yearUnit).setVisibility(mYearView.getVisibility());

        findViewById(R.id.tv_monthUnit).setVisibility(mMonthView.getVisibility());

        findViewById(R.id.tv_dayUnit).setVisibility(mDayView.getVisibility());
        findViewById(R.id.v_dayAndMinute).setVisibility(isShowHour ? View.VISIBLE : View.GONE);

        findViewById(R.id.tv_hourUnit).setVisibility(mHourView.getVisibility());

        findViewById(R.id.tv_minuteUnit).setVisibility(mMinuteView.getVisibility());

        initDatePicker();

        mYearView.removeChangingListener(yearWheelListener);
        mMonthView.removeChangingListener(monthWheelListener);
        mDayView.removeChangingListener(dayWheelListener);

        mYearView.addChangingListener(yearWheelListener);
        mMonthView.addChangingListener(monthWheelListener);
        mDayView.addChangingListener(dayWheelListener);

        }
    /**
     * 弹出日期时间选择器
     */
    private void initDatePicker() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
//        END_YEAR = year;
        String[] ymd = allowedSmallestTime.split("-");
        if(TextUtils.isEmpty(allowedBiggestTime))
            allowedBiggestTime = TimeUtil.getCurData();
        String[] ymdEnd= allowedBiggestTime.split("-");

        if (ymd.length > 2) {
            START_YEAR = Integer.parseInt(ymd[0]);
        }
        if(ymdEnd.length>2){
            END_YEAR = Integer.parseInt(ymdEnd[0]);
            month = Integer.parseInt(ymdEnd[1])-1;
            day= Integer.parseInt(ymdEnd[2]);
        }

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
        mYearView.setCyclic(false);// 循环滚动


        // 月
        int startMonth = 1;
        if (isOnlyThisMonth) {
            startMonth = curMonth + 1;
        }

        //初始年份最大值应该是当年最大月
        mMonthView.setAdapter(new NumericWheelAdapter(startMonth, year == curYear ? month + 1 : 12));
        mMonthView.setLabel("");
        mMonthView.setCurrentItem(isOnlyThisMonth ? 0 : curMonth != 0 ? curMonth : month);
        mMonthView.setCyclic(false);

        // 日
        //判断是否属于当前月份，如果不是，需要判断大小月，进行初始化
        if (curMonth < month){
            // 判断大小月及是否闰年,用来确定"日"的数据
            if (mListBig.contains(String.valueOf(curMonth))) {
                mDayView.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (mListLittle.contains(String.valueOf(curMonth))) {
                mDayView.setAdapter(new NumericWheelAdapter(1, 30));
            } else {
                if (((mYearView.getCurrentItem() + START_YEAR) % 4 == 0 && (mYearView.getCurrentItem() + START_YEAR) % 100 != 0)
                        || (mYearView.getCurrentItem() + START_YEAR) % 400 == 0)
                    mDayView.setAdapter(new NumericWheelAdapter(1, 29));
                else
                    mDayView.setAdapter(new NumericWheelAdapter(1, 28));
            }
        }else {
            if(mTimeType == TIME_TYPE.TYPE_END){
                mDayView.setAdapter(new NumericWheelAdapter(1, Integer.parseInt(ymdEnd[2])));
            }else if(mTimeType == TIME_TYPE.TYPE_START){
                mDayView.setAdapter(new NumericWheelAdapter(Integer.parseInt(ymd[2]), day));
            }else{
                mDayView.setAdapter(new NumericWheelAdapter(1, day));
            }
        }

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
            if (year_num < year) {
                mMonthView.setAdapter(new NumericWheelAdapter(1, 12));
            } else if (year_num >= year) {
                mMonthView.setAdapter(new NumericWheelAdapter(1, month + 1));
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
            onScroll();
            mMonthView.setCurrentItem(mMonthView.getCurrentItem());
            mDayView.setCurrentItem(mDayView.getCurrentItem());
        }
    };

    /**
     * 添加对"月"监听
     */
    private OnWheelChangedListener monthWheelListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            int month_num = newValue + 1;
            if (month_num == (month + 1)) {
                mDayView.setAdapter(new NumericWheelAdapter(1, day));
            } else {
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
            onScroll();
            mDayView.setCurrentItem(mDayView.getCurrentItem());
        }
    };

    /**
     * 添加对 日滚动控件 的添加
     */
    private OnWheelChangedListener dayWheelListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            mDayView.setCurrentItem(newValue);
            onScroll();
        }
    };

    private void onScroll() {

            int year = isOnlyThisYear ? Integer.parseInt(mYearView.getAdapter().getItem(0))
                    : mYearView.getCurrentItem() + START_YEAR;
            int month = isOnlyThisMonth ? Integer.parseInt(mMonthView.getAdapter().getItem(0))
                    : mMonthView.getCurrentItem() + 1;
            int day = mDayView.getCurrentItem() + 1;

        String monthS = String.format("%02d", month);
        String dayS = String.format("%02d", day);
        String yearS = String.format("%02d", year);


        if (mTimeType == TIME_TYPE.TYPE_START) {
            mSelectStartTime = yearS + "-" + monthS + "-" + dayS;
            mBeginTimeTv.setText(makeFormatContent(mContext.getString(R.string.begin_at), yearS + "." + monthS + "." + dayS));
        } else {
            mSelectEndTime = yearS + "-" + monthS + "-" + dayS;
            mEndTimeTv.setText(makeFormatContent(mContext.getString(R.string.end_at), yearS + "." + monthS + "." + dayS));
        }
    }

    /**
     * 格式化显示的数据，必须返回SpannableString对象
     *
     * @param priFix  前缀
     * @param content 内容
     * @return 返回格式化的数据
     */
    private SpannableString makeFormatContent(String priFix, String content){
        SpannableString spannableString = new SpannableString(priFix + content);
        spannableString.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.black33)),
                priFix.length(), spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(1.33f),
                priFix.length(), spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return spannableString;
    }


    /**
     * set监听
     *
     * @param onDateSelectFinished 完成监听
     */
    public void setOnDateSelectFinished(OnDateSelectFinished onDateSelectFinished) {
        this.onDateSelectFinished = onDateSelectFinished;
    }

    /**
     * 监听接口
     */
    public interface OnDateSelectFinished {
        /**
         * 监听方法
         *
         * @param startTime   开始时间
         * @param endTime  结束时间
         */
        void onSelectFinished(String startTime, String endTime);
    }

}
