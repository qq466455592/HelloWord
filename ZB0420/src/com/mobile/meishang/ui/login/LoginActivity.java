package com.mobile.meishang.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.LoginRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.FunctionUtil;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class LoginActivity extends MActivity implements
		ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private EditText mUserName;
	private EditText mPassword;
	private String mobile;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("登录美商");
		title.setVisibility(View.VISIBLE);
		mUserName = (EditText) findViewById(R.id.etext_username);
		mPassword = (EditText) findViewById(R.id.etext_password);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);

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

	// @Override
	// protected void onActivityResult(int requestCode, int ResultCode, Intent
	// data) {
	// // super.onActivityResult(arg0, arg1, arg2);
	// if (ResultCode == RESULT_OK) {
	//
	// switch (requestCode) {
	// case 1:
	// Bundle bundle = data.getExtras();
	// getSupportLoaderManager()
	// .restartLoader(RequestDistribute.LOGIN, bundle,
	// new LoginRequest(this));
	// break;
	//
	// default:
	// break;
	// }
	// }
	// }

	@Override
	public void updateUI(int identity, Object data) {
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.LOGIN:
			break;

		default:
			break;
		}
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.btn_login:
			netRequest();
			// LeShiHuiApplication.getInstance().setmGlobleUserId("123456");
			// setResult(RESULT_OK, null); //
			// intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
			// finish();// 此处一定要调用finish()方法
			break;
		case R.id.tv_regist:
			goActivity(RegisterActivity.class, null);
			// goActivityForResult(RegisterActivity.class, null, 1);
			break;
		case R.id.tv_forget_pd:
			goActivity(ForgotPdActivity.class, null);
			break;
//		case R.id.text_help:
//
//			break;

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
				if (identity == RequestDistribute.LOGIN) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		netRequest();

	}

	private void netRequest() {
		mobile = mUserName.getText().toString().trim();
		password = mPassword.getText().toString().trim();
		if (TextUtils.isEmpty(mobile)) {
			showToast("请输入账号");
		} else if (TextUtils.isEmpty(password)) {
			showToast("请输入密码");
		} else {
			if (!mLoadingView.isShown()) {
				mLoadingView.setVisibility(View.VISIBLE);
			}
			password = FunctionUtil.MD5(password);
			Bundle bundle = new Bundle();
			bundle.putString("mobile", mobile);
			bundle.putString("password", password);
			getSupportLoaderManager().restartLoader(RequestDistribute.LOGIN,
					bundle, new LoginRequest(this));
		}
	}
}
