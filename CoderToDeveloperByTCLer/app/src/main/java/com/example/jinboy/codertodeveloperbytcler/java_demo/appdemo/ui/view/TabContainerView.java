package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/05/22
 *     desc   : 用于底部滑动容器
 *     version: 1.0
 * </pre>
 */
public class TabContainerView extends LinearLayout {

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

    /** 默认颜色*/
    private int mTextNormalColor;
    /** 选中时颜色值*/
    private int mTextSelectedColor;

    /** 前一次选择位置*/
    private int mLastPosition;
    /** 当前选中位置*/
    private int mSelectedPosition;
    /**选择偏移位置 */
    private float mSelectionOffset;

    /** tab 标题*/
    private String[] mTitles;
    /** tab icon集合*/
    private int[][] mIconRes;

    /** tab item 视图集合*/
    private View[] mTabView;

    /** 布局文件id*/
    private int mLayoutId;
    /** textView 控件id*/
    private int mTextViewId;
    /** icon 控件id*/
    private int mIconVIewId;

    /**icon宽度*/
    private int mIconWidth;
    /**icon高度*/
    private int mIconHeight;

    /** 是否显示过渡颜色效果*/
    private boolean mShowTransitionColor = true;

    public TabContainerView(Context context) {
        this(context, null);
    }

    public TabContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initContainer (String[] titles, int[][] iconsRes, int[] colors, boolean showTransitionColor) {
        this.mTitles = titles;
        this.mIconRes = iconsRes;
        this.mTextNormalColor = getResources().getColor(colors[0]);
        this.mTextSelectedColor = getResources().getColor(colors[1]);
        this.mShowTransitionColor = showTransitionColor;
    }

    /**
     * 设置布局文件及相关控件id
     * @param layout layout布局文件 id
     * @param iconId ImageView 控件 id id <=0 时不显示
     * @param textId TextView 控件 id id <=0 时不显示
     * @param width  icon 宽度
     * @param height icon 高度
     */
    public void setContainerLayout (int layout, int iconId, int textId, int width, int height) {
        mLayoutId = layout;
        mTextViewId = textId;
        mIconVIewId = iconId;
        mIconWidth = width;
        mIconHeight = height;
    }

    /**
     * <p>设置布局文件及相关控件id --只有文本</p>
     * @param layout layout布局文件 id
     * @param textId TextView 控件 id
     * @param width  icon 宽度
     * @param height icon 高度
     */
    public void setSingleTextLayout (int layout, int textId, int width, int height) {
        setContainerLayout(layout, 0, textId, width, height);

    }

    /**
     * <p>设置布局文件及相关控件id</p>
     * @param layout layout布局文件 id
     * @param iconId ImageView 控件 id
     * @param width  icon 宽度
     * @param height icon 高度
     */
    public void setSingleIconLayout (int layout, int iconId, int width, int height) {
        setContainerLayout(layout, iconId, 0, width,  height);
    }

    public void setViewPager(ViewPager viewPager) {
        removeAllViews();
        mViewPager = viewPager;
        if (viewPager != null && viewPager.getAdapter() != null) {
            viewPager.addOnPageChangeListener(new InternalViewPagerListener());
            addTabViewToContainer();
        }
    }

    /**
     * <p>添加tab view到当前容器</p>
     */
    private void addTabViewToContainer() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        mTabView = new View[adapter.getCount()];

        for (int index = 0, len = adapter.getCount(); index < len; index++) {

            final View tabView = LayoutInflater.from(getContext()).inflate(mLayoutId, this, false);
            mTabView[index] = tabView;

            /*tabIconView初始化*/
            TabIconView iconView = null;
            if (mIconVIewId > 0) {
                iconView = (TabIconView) tabView.findViewById(mIconVIewId);
                iconView.init(mIconRes[index][0], mIconRes[index][1], mIconWidth, mIconHeight);
            }

            /*tabTextView初始化*/
            TextView textView = null;
            if (mTextViewId > 0) {
                textView = (TextView) tabView.findViewById(mTextViewId);
                textView.setText(mTitles[index]);

            }

            /*设置宽度，等分container*/
            LayoutParams lp = (LayoutParams) tabView.getLayoutParams();
            lp.width = 0;
            lp.weight = 1;

            /*添加tab点击事件*/
            addTabOnClickListener(tabView, index);

            /*设置当前状态*/
            if (index == mViewPager.getCurrentItem()) {
                if (iconView != null) {
                    iconView.offsetChanged(0);
                }
                tabView.setSelected(true);
                if (textView != null) {
                    textView.setTextColor(mTextSelectedColor);
                }
            }

            addView(tabView);
        }
    }

    /**
     * <p>viewPager滑动改变监听事件</p>
     */
    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            onViewPagerPageChanged(position, positionOffset);

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {

            for (int i = 0; i < getChildCount(); i++) {
                if (mIconVIewId > 0) {
                    ((TabIconView) mTabView[i].findViewById(mIconVIewId)) .offsetChanged(position == i ? 0 : 1);
                }
                if (mTextViewId > 0) {
                    ((TextView) mTabView[i].findViewById(mTextViewId)) .setTextColor(position == i ? mTextSelectedColor : mTextNormalColor);
                }
            }

            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                onViewPagerPageChanged(position, 0f);
            }

            for (int i = 0, size = getChildCount(); i < size; i++) {
                getChildAt(i).setSelected(position == i);
            }


            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    }

    /**
     * viewpager滑动改变后更新当前container视图
     * @param position 当前选择珀斯tion
     * @param positionOffset position 偏移量
     */
    private void onViewPagerPageChanged(int position, float positionOffset) {
        mSelectedPosition = position;
        mSelectionOffset = positionOffset;
        if (positionOffset == 0f && mLastPosition != mSelectedPosition) {
            mLastPosition = mSelectedPosition;
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int childCount = getChildCount();
        if (childCount > 0) {
            /*当发生便宜时，绘制渐变区域*/
            if (mSelectionOffset > 0f && mSelectedPosition < (getChildCount() - 1) && mShowTransitionColor) {

                /*获取当前tab和下一tab view */
                View selectedTab = getChildAt(mSelectedPosition);
                View nextTab = getChildAt(mSelectedPosition + 1);

                /*显示tab icon时,刷新各自view 透明度*/
                if (mIconVIewId > 0) {
                    View selectedIconView = selectedTab.findViewById(mIconVIewId);
                    View nextIconView = nextTab.findViewById(mIconVIewId);

                    //draw icon alpha
                    if (selectedIconView instanceof TabIconView && nextIconView instanceof TabIconView) {
                        ((TabIconView) selectedIconView).offsetChanged(mSelectionOffset);
                        ((TabIconView) nextIconView).offsetChanged(1 - mSelectionOffset);
                    }
                }

                 /*显示tab text,刷新各自view 透明度*/
                if  (mTextViewId > 0) {
                    View selectedTextView = selectedTab.findViewById(mTextViewId);
                    View nextTextView = nextTab.findViewById(mTextViewId);

                    //draw text color
                    Integer selectedColor = (Integer) evaluate(mSelectionOffset, mTextSelectedColor, mTextNormalColor);
                    Integer nextColor = (Integer) evaluate(1 - mSelectionOffset, mTextSelectedColor, mTextNormalColor);

                    if (selectedTextView instanceof TextView && nextTextView instanceof TextView) {
                        ((TextView) selectedTextView).setTextColor(selectedColor);
                        ((TextView) nextTextView).setTextColor(nextColor);
                    }
                }

            }
        }
    }

    /**
     * tab item点击事件,点击时直接跳到对应view
     * @param view        View
     * @param position position
     */
    private void addTabOnClickListener (View view, final int position) {
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(position, false);
            }
        };
        view.setOnClickListener(listener);
    }


    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPagerPageChangeListener = listener;
    }

    /**
     * This function returns the calculated in-between value for a color
     * given integers that represent the start and end values in the four
     * bytes of the 32-bit int. Each channel is separately linearly interpolated
     * and the resulting calculated values are recombined into the return value.
     *
     * @param fraction The fraction from the starting to the ending values
     * @param startValue A 32-bit int value representing colors in the
     * separate bytes of the parameter
     * @param endValue A 32-bit int value representing colors in the
     * separate bytes of the parameter
     * @return A value that is calculated to be the linearly interpolated
     * result, derived by separating the start and end values into separate
     * color channels and interpolating each one separately, recombining the
     * resulting values in the same way.
     */
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (startA + (int)(fraction * (endA - startA))) << 24 |
                (startR + (int)(fraction * (endR - startR))) << 16 |
                (startG + (int)(fraction * (endG - startG))) << 8 |
                (startB + (int)(fraction * (endB - startB)));
    }

    public int getTextSelectedColor() {
        return mTextSelectedColor;
    }

    public void setTextSelectedColor(int textSelectedColor) {
        mTextSelectedColor = textSelectedColor;
    }

    public int getTextNormalColor() {
        return mTextNormalColor;
    }

    public void setTextNormalColor(int textNormalColor) {
        mTextNormalColor = textNormalColor;
    }

    public int getLastPosition() {
        return mLastPosition;
    }

    public void setLastPosition(int lastPosition) {
        mLastPosition = lastPosition;
    }

    public int getSelectedPosition() {
        return mSelectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        mSelectedPosition = selectedPosition;
    }

    public float getSelectionOffset() {
        return mSelectionOffset;
    }

    public void setSelectionOffset(float selectionOffset) {
        mSelectionOffset = selectionOffset;
    }

    public String[] getTitles() {
        return mTitles;
    }

    public void setTitles(String[] titles) {
        mTitles = titles;
    }

    public int[][] getIconRes() {
        return mIconRes;
    }

    public void setIconRes(int[][] iconRes) {
        mIconRes = iconRes;
    }

    public View[] getTabView() {
        return mTabView;
    }

    public void setTabView(View[] tabView) {
        mTabView = tabView;
    }

    public boolean isShowTransitionColor() {
        return mShowTransitionColor;
    }

    public void setShowTransitionColor(boolean showTransitionColor) {
        mShowTransitionColor = showTransitionColor;
    }
}
