package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;

/**
 * 内嵌网页
 * Created by 王建 on 2016/12/05.
 */
public class WebViewActivity extends Activity {

	public static void startWebViewActivity (Context activity,  String url) {
		Intent intent = new Intent(activity, WebViewActivity.class);
		intent.putExtra("url", url);
		activity.startActivity(intent);
	}

	/**
	 * 启动WebViewActivity 静态方法
	 * @param activity Activity
	 * @param title 标题
	 * @param url url
     * @param type 1:红包;其他待定义
     */
	public static void startWebViewActivity (Activity activity, String title, String url, int type) {
		Intent intent = new Intent(activity, WebViewActivity.class);
		intent.putExtra("title", title);
		intent.putExtra("url", url);
		intent.putExtra("type", type);
		activity.startActivity(intent);
	}

	private WebView mWebView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_activity);
		createView();
	}

	private void createView() {

		mWebView = (WebView) findViewById(R.id.wv_web);
		mWebView.getSettings().setDefaultFontSize(14);
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
		mWebView.loadUrl(getIntent().getStringExtra("url"));


		
		mWebView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				setProgress(progress * 100);
			}
		});
		
		mWebView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(url);
				return true;
			}
		});
	}

	public boolean onKeyDown(int keyCode, @Nullable KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
