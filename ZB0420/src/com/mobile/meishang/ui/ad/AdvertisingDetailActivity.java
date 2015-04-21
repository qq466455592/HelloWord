package com.mobile.meishang.ui.ad;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.R;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.GetRightsCodeRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.RightsCode;
import com.mobile.meishang.ui.login.LoginActivity;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class AdvertisingDetailActivity extends MActivity implements
		ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private WebView webview;
	private Bundle mBundle;

	// private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBundle = getIntent().getBundleExtra("bundle");
		setContentView(R.layout.activity_agreement);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText(mBundle.getString("name", ""));
		title.setVisibility(View.VISIBLE);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		webview = (WebView) findViewById(R.id.web);
		// webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webview.getSettings().setJavaScriptEnabled(true);
		String url = mBundle.getString("url", "");
		webview.loadUrl(url);
		webview.addJavascriptInterface(new JavaScripdtObject(), "mobile");
		webview.setWebViewClient(new MWebViewClient());
		// intent = new Intent(this, AlipayActivity.class);
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	private class JavaScripdtObject {

		@JavascriptInterface
		// 加入这个注解
		public void clickOnAndroid(final String msg) {
			runOnUiThread(new Runnable() {

				public void run() {
					// showToast(msg);
					if (MApplication.getInstance().checkLogin()) {
						net();
					} else {
						goActivity(LoginActivity.class, null);
					}
				}

			});
		}
	}

	// Web视图
	private class MWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			mLoadingView.setVisibility(View.GONE);
			super.onPageFinished(view, url);
		}
	}

	@Override
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		switch (identity) {
		case RequestDistribute.GET_RIGHTS_CODE:
			RightsCode rightCode = (RightsCode) data;
			if (rightCode.isSuccess()) {
				// try {
				// intent.putExtra("bundle", mBundle);
				// intent.putExtra("bankSeq", rightCode.getBankSeq());
				// } catch (Exception ex) {
				// }
				// startActivity(intent);
				mBundle.putString("bankSeq", rightCode.getBankSeq());
				mBundle.putString("notifyUrl", rightCode.getcallBackUrl());
//				goActivity(AlipayActivity.class, mBundle);
			} else {
				showToast(rightCode.getCodeMessage());
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void handleException(final int identity, final Exception e) {
		super.handleException(identity, e);
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.GET_RIGHTS_CODE) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		net();
	}

	private void net() {
		getSupportLoaderManager().restartLoader(
				RequestDistribute.GET_RIGHTS_CODE, mBundle,
				new GetRightsCodeRequest(this));

	}
}
