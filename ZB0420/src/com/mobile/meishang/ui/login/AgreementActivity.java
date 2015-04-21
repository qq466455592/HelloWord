//package com.mobile.meishang.ui.login;
//
//import android.os.Bundle;
//import android.view.View;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.TextView;
//
//import com.mobile.meishang.MActivity;
//import com.mobile.meishang.MApplication;
//import com.mobile.meishang.R;
//import com.mobile.meishang.utils.view.LoadingView;
//import com.umeng.analytics.MobclickAgent;
//
//public class AgreementActivity extends MActivity {
//	private LoadingView mLoadingView;
//	private WebView webview;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_agreement);
//		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
//		TextView title = (TextView) findViewById(R.id.top_name);
//		title.setText(R.string.agreement);
//		title.setVisibility(View.VISIBLE);
//		mLoadingView = (LoadingView) findViewById(R.id.loading);
//		webview = (WebView) findViewById(R.id.web);
//		// webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//		// webview.getSettings().setJavaScriptEnabled(true);
//		String url = MApplication.getInstance().getmConfig().urlRoot
//				+ "app_agreement.html";
//		webview.loadUrl(url);
//		webview.setWebViewClient(new MWebViewClient());
//
//	}
//
//	@Override
//	public void onResume() {
//		super.onResume();
//		MobclickAgent.onResume(this);
//	}
//
//	@Override
//	public void onPause() {
//		super.onPause();
//		MobclickAgent.onPause(this);
//	}
//
//	// Web视图
//	private class MWebViewClient extends WebViewClient {
//		@Override
//		public boolean shouldOverrideUrlLoading(WebView view, String url) {
//			view.loadUrl(url);
//			return true;
//		}
//
//		@Override
//		public void onPageFinished(WebView view, String url) {
//			mLoadingView.setVisibility(View.GONE);
//			super.onPageFinished(view, url);
//		}
//	}
//}
