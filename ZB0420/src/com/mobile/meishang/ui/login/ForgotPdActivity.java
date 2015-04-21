package com.mobile.meishang.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class ForgotPdActivity extends MActivity implements ExceptionHandler,
		LoadEvent {
	private EditText mPhone;
	private EditText mPhoneCode;
	private EditText mNewPd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_pd);
		findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("重置密码");
		TextView tv_top_right = (TextView) findViewById(R.id.tv_top_right);
		tv_top_right.setText("下一步");
		tv_top_right.setVisibility(View.VISIBLE);
		mPhone = (EditText) findViewById(R.id.etext_phone_number);
		mPhoneCode = (EditText) findViewById(R.id.etext_verification_code);
		mNewPd = (EditText) findViewById(R.id.etext_password);
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
	public void updateUI(int identity, Object data) {
		super.updateUI(identity, data);
	}

	@Override
	public void handleException(int identity, Exception e) {
		super.handleException(identity, e);
	}

	@Override
	public void retryAgain(View v) {

	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.tv_top_right:
			goActivity(RetrievedResetPasswordActivity.class, null);
			break;

		default:
			break;
		}
	}
}
