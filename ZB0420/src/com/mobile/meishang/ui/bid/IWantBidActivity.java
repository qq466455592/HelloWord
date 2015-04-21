package com.mobile.meishang.ui.bid;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.model.RequestDistribute;
import com.umeng.analytics.MobclickAgent;

public class IWantBidActivity extends MActivity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_i_want_bid);
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
	public void handleException(final int identity, final Exception e) {
		// super.handleException(identity, e);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.COUPON_GET) {
					// mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void updateUI(int identity, Object data) {
		if (data != null) {
			switch (identity) {
			case RequestDistribute.ADVERTISING_GALLERY_ONE_DAY:
				break;

			default:
				break;
			}
		} else {
			// showToast("数据对象空");
		}
	}

	@Override
	public void resetUI(int identity, Object data) {

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.llayout_gowhere:
		// // goActivity(GoWhereActivity.class, null);
		// break;
		// case R.id.llayout_write_notes:
		// goActivity(MyTravelNotesListActivity.class, null);
		// break;
		// case R.id.llayout_active:
		// // goActivity(InitiateActivityActivity.class, null);
		// goActivity(TravelNotesDetailActivity.class, null);
		// break;
		// case R.id.llayout_near:
		// break;

		default:
			break;
		}
	}

}
