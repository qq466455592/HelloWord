package com.mobile.meishang.ui.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.FeedbackRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class FeedbackActivity extends MActivity implements
		ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private EditText mContentEditText;
	private EditText mcontactWayEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText(R.string.feedback);
		title.setVisibility(View.VISIBLE);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mContentEditText = (EditText) findViewById(R.id.edit_content);
		mcontactWayEditText = (EditText) findViewById(R.id.edit_contact_way);

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
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.FEEDBACK:
//			RequestResponseInfo info = (RequestResponseInfo) data;
//			String code = info.getErrorCode();
//			if (TextUtils.isEmpty(code)) {
//				showToast("反馈成功");
//				finish();
//
//			} else {
//				if ("1".equals(code)) {
//					goActivity(LoginActivity.class, null);
//				} else {
//					showToast(info.getErrorMessage());
//				}
//			}

			break;

		default:
			break;
		}
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.btn_sub:
			String info = mContentEditText.getText().toString();
			if (TextUtils.isEmpty(info)) {
				showToast("请输入您的宝贵意见");
				break;
			}
			String contactWay = mcontactWayEditText.getText().toString();
			if (TextUtils.isEmpty(contactWay)) {
				showToast("请输入您联系方式，以便我们给您回复");
				break;
			}
			mLoadingView.setVisibility(View.VISIBLE);
			Bundle bundle = new Bundle();
			bundle.putString("info", info);
			bundle.putString("contactWay", contactWay);
			getSupportLoaderManager().restartLoader(RequestDistribute.FEEDBACK,
					bundle, new FeedbackRequest(this));
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
				if (identity == RequestDistribute.FEEDBACK) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		String info = mContentEditText.getText().toString();
		String contactWay = mcontactWayEditText.getText().toString();
		if (TextUtils.isEmpty(info)) {
			showToast("请输入您的宝贵意见");
		} else if (TextUtils.isEmpty(contactWay)) {
			showToast("请输入您联系方式，以便我们给您回复");
		} else {
			Bundle bundle = new Bundle();
			bundle.putString("info", info);
			bundle.putString("contactWay", contactWay);
			getSupportLoaderManager().restartLoader(RequestDistribute.FEEDBACK,
					bundle, new FeedbackRequest(this));
		}

	}
}
