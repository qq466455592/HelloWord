package com.mobile.meishang.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.RegisterRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.FunctionUtil;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

/**
 * 注册一
 * 
 * @author Administrator
 * 
 */
public class RegisterActivity extends MActivity implements
		ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private EditText etext_phone_number;
	private EditText etext_verification_code;
	private EditText etext_password;
	private Bundle mBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("加入美商");
		title.setVisibility(View.VISIBLE);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		etext_phone_number = (EditText) findViewById(R.id.etext_phone_number);
		etext_verification_code = (EditText) findViewById(R.id.etext_verification_code);
		etext_password = (EditText) findViewById(R.id.etext_password);
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

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.tv_get_verification_code:
			break;
		case R.id.btn_finish:
			String mobile = etext_phone_number.getText().toString().trim();
			String password = etext_password.getText().toString().trim();
			mBundle = new Bundle();
			mBundle.putString("mobile", mobile);
			mBundle.putString("password", FunctionUtil.MD5(password));
			mLoadingView.setVisibility(View.VISIBLE);
			getSupportLoaderManager().restartLoader(RequestDistribute.REGISTER,
					mBundle, new RegisterRequest(this));

			break;

		default:
			break;
		}
	}

	@Override
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.REGISTER:
//			RequestResponseInfo requestResponseInfo = (RequestResponseInfo) data;
//			if ("".equals(requestResponseInfo.getErrorCode())) {
//				LeShiHuiApplication.setSessionId(requestResponseInfo
//						.getSessionId());
//				// Intent intent = new Intent();
//				// intent.putExtra("bundle", mBundle);
//				// setResult(1, intent);
//				finish();
//			} else {
//				showToast(requestResponseInfo.getErrorMessage());
//			}
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
				if (identity == RequestDistribute.REGISTER) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		getSupportLoaderManager().restartLoader(RequestDistribute.REGISTER,
				mBundle, new RegisterRequest(this));
	}

}
