package com.mobile.meishang.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.umeng.analytics.MobclickAgent;

/**
 * 找回密码二:重新设置密码
 * 
 * @author Administrator
 * 
 */
public class RetrievedResetPasswordActivity extends MActivity {
	private EditText etext_password;
	private EditText etext_pd_repeat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrieved_reset_password);
		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText(R.string.retrieved_password);
		etext_password = (EditText) findViewById(R.id.etext_password);
		etext_pd_repeat = (EditText) findViewById(R.id.etext_pd_repeat);

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
		case R.id.btn_finish:

			break;

		default:
			break;
		}
	}
}
