package com.mobile.meishang.ui;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.R;
import com.mobile.meishang.config.Constants;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.LoginRequest;
import com.mobile.meishang.core.request.VersionRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Version;
import com.mobile.meishang.ui.home.TabActivity;
import com.mobile.meishang.utils.FunctionUtil;
import com.umeng.analytics.MobclickAgent;

public class WelcomeScreenActivity extends MActivity implements
		ExceptionHandler {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		if (FunctionUtil.isConnect(this))// 判断是否有网络
		{
			String user = MApplication.getInstance().getmConfig()
					.getPreferencesVal(Constants.USER_NAME, "");
			String password = MApplication.getInstance().getmConfig()
					.getPreferencesVal(Constants.PASSWORD, "");
			if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {
				Bundle bundle = new Bundle();
				bundle.putString("mobile", user);
				bundle.putString("password", password);
				getSupportLoaderManager().initLoader(RequestDistribute.LOGIN,
						bundle, new LoginRequest(this));
			} else {
				getSupportLoaderManager().initLoader(RequestDistribute.VERSION,
						null, new VersionRequest(this));
			}
		} else {
			displayNetWorkErrorExitAppDialog();
			// showToast("网络连接错误，请设置网络");
		}
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
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		if (data != null) {
			switch (identity) {
			case RequestDistribute.VERSION:
				Version version = (Version) data;
				if (MApplication.getInstance().getmConfig()
						.getGuideFlag() != 0) {
					MApplication
							.getInstance()
							.getmConfig()
							.putPreferencesVal(Constants.VERSION_CODE,
									version.getVersionid());
					goActivity(TabActivity.class, null);
				} else {
					MApplication.getInstance().getmConfig()
							.setGuideFlag(1);
					goActivity(SlidingAroundGuideActivity.class, null);

				}
				break;
			case RequestDistribute.LOGIN:
//				RequestResponseInfo requestResponseInfo = (RequestResponseInfo) data;
//				if ("".equals(requestResponseInfo.getErrorCode())) {
//					MApplication.setSessionId(requestResponseInfo
//							.getSessionId());
//				}
//				getSupportLoaderManager().initLoader(RequestDistribute.VERSION,
//						null, new VersionRequest(this));
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @Description:提示网络连接信息
	 * @Author 11120500
	 * @Date 2013-4-25
	 */
	public void displayNetWorkErrorExitAppDialog() {
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setCancelable(false);
		dialog.show();
		Window window = dialog.getWindow();// *** 主要就是在这里实现这种效果的.//
		window.setContentView(R.layout.dialog_exit);
		TextView content = (TextView) window.findViewById(R.id.tv_content);
		content.setText("网络错误，请设置");
		Button cancelBtn = (Button) window.findViewById(R.id.btn_a);
		cancelBtn.setText("取消");
		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				finish();
			}
		});
		Button exitBtn = (Button) window.findViewById(R.id.btn_b);
		exitBtn.setText("设置");
		exitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent intent = new Intent("/");
				ComponentName cm = new ComponentName("com.android.settings",
						"com.android.settings.Settings");
				intent.setComponent(cm);
				intent.setAction("android.intent.action.VIEW");
				startActivity(intent);
				finish();

			}
		});

	}

}
