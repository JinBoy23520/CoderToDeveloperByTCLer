package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;


/**
 * RoundedRelativeLayout.java
 *
 * @author wangj
 * @version 1.0
 * @date 2013-1-11 上午11:42:47
 */
public class RoundedLinearLayout extends LinearLayout {
    /***/
    private Path mClip;
    /**
     * 圆角
     */
    private float mRoundConner;

    /**
     * @param context
     * @param attrs
     */
    @SuppressLint("NewApi")
    public RoundedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion >= 11) {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.roundConner);
        mRoundConner = typeArray.getDimension(R.styleable.roundConner_round, 0);
        typeArray.recycle();

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mClip = new Path();
        RectF rect = new RectF(1, 1, w - 1, h - 1);
        if (mRoundConner == 0f) {
            mRoundConner = getResources().getDimensionPixelSize(R.dimen.round_radius);
        }
        mClip.addRoundRect(rect, mRoundConner, mRoundConner, Direction.CW);
    }

    /**
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        /*抗锯齿*/
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.clipPath(mClip);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

}
