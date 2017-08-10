package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;



/**
 * 标题栏View
 */
public class TitleView extends RelativeLayout {

	/**
	 * 容器
	 */
	private View mContainer;

	/**
	 * 居中标题 -- 当显示居中标题时不显示左侧标题
	 */
	public TextView mTitleCenterTV;
	/**
	 * 左侧返回按钮
	 */
	public ImageView mLeftButtonIV;
	/**
	 * 左侧返回按钮
	 */
	public TextView mLeftBackTv;
	/**
	 * 右侧图片
	 */
	public ImageView mRightButtonIV;
	/**
	 * 右侧文字
	 */
	public TextView mRightButtonTV;

	/**
	 * 右侧第二个图片按钮
	 */
	public ImageView mRightTwoIv;
	/**
	 * 右侧第二个文字按钮
	 */
	public TextView mRightTwoTv;

	public Context context;
	public TitleView(Context context) {
		this(context, null);
	}

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	public TitleView(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
		initView(context, attrs);
	}

	public void setBackgroundResource(int resid) {
		mContainer.setBackgroundResource(resid);
	}

	/**
	 * <li> 初始化 </li>
	 *
	 * @param context Context
	 * @param attrs   AttributeSet
	 */
	private void initView(Context context, AttributeSet attrs) {
		mContainer = LayoutInflater.from(context).inflate(
				R.layout.title_view, null);
		addView(mContainer);
		mTitleCenterTV = (TextView) mContainer.findViewById(R.id.tv_title);
		mLeftButtonIV = (ImageView) mContainer.findViewById(R.id.iv_title_left_button);

		/*标题显示在左侧*/
		mLeftBackTv = (TextView) mContainer.findViewById(R.id.tv_title_left_button);

		/*右侧显示*/
		mRightButtonIV = (ImageView) mContainer.findViewById(R.id.iv_title_right_button);
		mRightButtonTV = (TextView) mContainer.findViewById(R.id.tv_title_right_button);

		/*右侧第二个按钮区域*/
		mRightTwoIv = (ImageView) mContainer.findViewById(R.id.iv_title_right_button_two);
		mRightTwoTv = (TextView) mContainer.findViewById(R.id.tv_title_right_button_two);

		TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.TitleAttr);
		boolean titleLeftBack = typeArray.getBoolean(R.styleable.TitleAttr_title_left_back, true);

		/*标题文字及显示位置*/
		int leftText = typeArray.getResourceId(R.styleable.TitleAttr_title_left_text, 0);
//		setLeftText(
//				leftText > 0 ? typeArray.getResources().getText(leftText)
//						: typeArray.getString(R.styleable.TitleAttr_title_left_text));
		int leftTextColor = typeArray.getResourceId(R.styleable.TitleAttr_title_left_text_color, 0);
		if (leftTextColor > 0) {
			setLeftTextColor(leftTextColor);
		}
        /*左侧标题drawable*/
		int leftDrawable = typeArray.getResourceId(R.styleable.TitleAttr_title_left_text_drawable_left, 0);
		int rightDrawable = typeArray.getResourceId(R.styleable.TitleAttr_title_left_text_drawable_right, 0);
		setLeftTextViewDrawable(leftDrawable, 0, rightDrawable, 0);

		/*标题文字及显示位置*/
		int titleText = typeArray.getResourceId(R.styleable.TitleAttr_title_name, 0);
		setTitle(
				titleText > 0 ? typeArray.getResources().getText(titleText)
						: typeArray.getString(R.styleable.TitleAttr_title_name));

		/*右侧文字按钮*/
		int rightText = typeArray.getResourceId(R.styleable.TitleAttr_title_right_text, 0);
		setRightText(rightText > 0 ? typeArray.getResources().getText(rightText) : typeArray
				.getString(R.styleable.TitleAttr_title_right_text));
		int rightTextColor = typeArray.getResourceId(R.styleable.TitleAttr_title_left_text_color, 0);
		if (rightTextColor > 0) {
			setRightTextColor(rightTextColor);
		}
		/*右侧文字drawable*/
		int rightTextleftDrawable = typeArray.getResourceId(R.styleable.TitleAttr_title_right_text_drawable_left, 0);
		int rightTextrightDrawable = typeArray.getResourceId(R.styleable.TitleAttr_title_right_text_drawable_right, 0);
		setRightTextViewDrawable(rightTextleftDrawable, 0, rightTextrightDrawable, 0);

		/*右侧图片按钮*/
		int rightImg = typeArray.getResourceId(R.styleable.TitleAttr_title_right_image, 0);
		if (rightImg != 0) {
			setRightImage(typeArray.getResources().getDrawable(rightImg));
		}

		/*右侧文字按钮--第二个*/
		int rightTextTwo = typeArray.getResourceId(R.styleable.TitleAttr_title_right_text_two, 0);
		setRightTwoText(rightTextTwo > 0 ? typeArray.getResources().getText(rightTextTwo) : typeArray
				.getString(R.styleable.TitleAttr_title_right_text_two));
		/*右侧文字drawable*/
		int rightTextTwoleftDrawable = typeArray.getResourceId(R.styleable.TitleAttr_title_right_text_two_drawable_left, 0);
		int rightTextTworightDrawable = typeArray.getResourceId(R.styleable.TitleAttr_title_right_text_two_drawable_right, 0);
		setRightTextTwoViewDrawable(rightTextTwoleftDrawable, 0, rightTextTworightDrawable, 0);


		/*右侧图片按钮--第二个*/
		int rightImgTwo = typeArray.getResourceId(R.styleable.TitleAttr_title_right_image_two, 0);
		if (rightImgTwo != 0) {
			setRightTwoImage(typeArray.getResources().getDrawable(rightImgTwo));
		}

		/*重设 高度*/
		android.view.ViewGroup.LayoutParams params = mContainer.getLayoutParams();
		params.height = typeArray.getDimensionPixelSize(R.styleable.TitleAttr_title_height, getResources()
				.getDimensionPixelSize(R.dimen.title_bar_height));
		mContainer.setLayoutParams(params);
		typeArray.recycle();
	}

	private void setLeftTextViewDrawable(int left, int top, int right, int bottom) {
		mLeftBackTv.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
	}


	private void setRightTextViewDrawable(int left, int top, int right, int bottom) {
		mRightButtonTV.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
	}

	private void setRightTextTwoViewDrawable(int left, int top, int right, int bottom) {
		mRightTwoTv.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
	}

	/**
	 * <li> 设置标题 </li>
	 *
	 * @param title int
	 */
	public void setTitle(int title) {
		if (title != 0) {
			mTitleCenterTV.setText(title);
		}
	}

	/**
	 * <li> 设置左侧按钮 </li>
	 *
	 * @param text CharSequence
	 */
	public void setLeftText(CharSequence text) {
		mLeftBackTv.setText(text);
		if (!TextUtils.isEmpty(text)) {
			mLeftBackTv.setVisibility(View.VISIBLE);
		}
	}

	public void setLeftTextColor(int id) {
		mLeftBackTv.setTextColor(id);
	}

	public void setRightTextColor(int id) {
		mRightButtonTV.setTextColor(id);
	}

	public void setTitleColor(int id) {
		mTitleCenterTV.setTextColor(id);
	}

	/**
	 * <li> 设置标题 </li>
	 *
	 * @param title CharSequence
	 */
	public void setTitle(CharSequence title) {
		mTitleCenterTV.setText(title);
	}


	public void setTitleGravity(int gravity) {
		mTitleCenterTV.setGravity(gravity);
	}

	/**
	 * <li> 左边本地图片 </li>
	 *
	 * @param imageSelector int
	 */
	public void setLeftImage(int imageSelector) {
		mLeftButtonIV.setImageResource(imageSelector);
		mLeftButtonIV.setVisibility(View.VISIBLE);
	}

	/**
	 * <li> 左边点击返回上一页面 </li>
	 *
	 * @param activity Activity
	 */
	public void setLeftToBack(final Activity activity) {
		mLeftButtonIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activity.onBackPressed();
			}
		});

		mLeftButtonIV.setVisibility(View.VISIBLE);
	}

	/**
	 * <li> 左边点击返回上一页面 </li>
	 *
	 * @param activity Activity
	 */
	public void setLeftTextClickToBack(final Activity activity) {
		mLeftBackTv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activity.onBackPressed();
			}
		});

		mLeftBackTv.setVisibility(View.VISIBLE);
	}


	/**
	 * <li> 右边显示图片资源id </li>
	 *
	 * @param image int
	 */
	public void setRightImage(int image) {
		mRightButtonIV.setImageResource(image);
		mRightButtonIV.setVisibility(View.VISIBLE);
		mRightButtonTV.setVisibility(View.GONE);
	}

	/**
	 * <li> 右边drawable  </li>
	 *
	 * @param image Drawable
	 */
	public void setRightImage(Drawable image) {
		mRightButtonIV.setVisibility(View.VISIBLE);
		mRightButtonIV.setImageDrawable(image);
		mRightButtonTV.setVisibility(View.GONE);
	}

	/**
	 * <li> 右边显示图片资源id --two</li>
	 *
	 * @param image int
	 */
	public void setRightTwoImage(int image) {
		mRightTwoIv.setImageResource(image);
		mRightTwoIv.setVisibility(View.VISIBLE);
		mRightTwoTv.setVisibility(View.GONE);
	}

	/**
	 * <li> 右边显示突变drawable --two </li>
	 *
	 * @param image Drawable
	 */
	public void setRightTwoImage(Drawable image) {
		mRightTwoIv.setImageDrawable(image);
		mRightTwoIv.setVisibility(View.VISIBLE);
		mRightTwoTv.setVisibility(View.GONE);
	}

	/**
	 * <li> 右边显示文本按钮资源ID </li>
	 *
	 * @param text int
	 */
	public void setRightText(int text) {
		mRightButtonTV.setText(text);
		mRightButtonTV.setVisibility(View.VISIBLE);
		mRightButtonIV.setVisibility(View.GONE);
	}

	/**
	 * <li> 右边显示文本按钮资源ID ---第二个</li>
	 *
	 * @param text int
	 */
	public void setRightTwoText(int text) {
		mRightTwoTv.setText(text);
		mRightTwoTv.setVisibility(View.VISIBLE);
		mRightTwoIv.setVisibility(View.GONE);
	}

	/**
	 * <li> 右边显示文本按钮第二个 </li>
	 *
	 * @param text CharSequence
	 */
	public void setRightText(CharSequence text) {
		mRightButtonTV.setText(text);
		mRightButtonTV.setVisibility(View.VISIBLE);
		mRightButtonIV.setVisibility(View.GONE);
	}

	/**
	 * <li> 右边显示文本按钮 </li>
	 *
	 * @param text CharSequence
	 */
	public void setRightTwoText(CharSequence text) {
		mRightTwoTv.setText(text);
		mRightTwoTv.setVisibility(View.VISIBLE);
		mRightTwoIv.setVisibility(View.GONE);
	}

	/**
	 * <li> 右边图片按钮是否可见 </li>
	 *
	 * @param isVisible boolean
	 */
	public void setRightImageVisible(boolean isVisible) {
		if (isVisible) {
			mRightButtonIV.setVisibility(View.VISIBLE);
		} else {
			mRightButtonIV.setVisibility(View.GONE);
		}
	}

	/**
	 * <li> 右边文字按钮是否可见 </li>
	 *
	 * @param isVisible boolean
	 */
	public void setRightTextVisible(boolean isVisible) {
		if (isVisible) {
			mRightButtonTV.setVisibility(View.VISIBLE);
		} else {
			mRightButtonTV.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置标题栏颜色
	 * @param color
	 */
	public void setContainerBackgroundColor(int color) {
		mContainer.findViewById(R.id.title_bar).setBackgroundColor(getResources().getColor(color));
	}

}
