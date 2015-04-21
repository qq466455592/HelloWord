package com.mobile.meishang.ui.bid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.BidDetailGridviewAdapter;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.ui.infomation.InfoListActivity;
import com.mobile.meishang.ui.lehuigou.GoodsSearchActivity;
import com.mobile.meishang.ui.lehuigou.LehuigoHomeActvity;
import com.mobile.meishang.ui.share.SharedActivity;
import com.mobile.meishang.ui.widget.GridViewWithHeaderAndFooter;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.view.ShareActivity;

public class BidDetailActivity extends MActivity implements OnClickListener {
	private GridViewWithHeaderAndFooter mGridView;
	private BidDetailGridviewAdapter mAdapter;
	private View headView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bid_detail);
		mGridView = (GridViewWithHeaderAndFooter) findViewById(R.id.gridview);
		headView = LayoutInflater.from(this).inflate(
				R.layout.layout_bid_detail_hview, null);
		mGridView.addHeaderView(headView, null, false);
		mAdapter = new BidDetailGridviewAdapter(this);
		mGridView.setAdapter(mAdapter);
		// mGridView.setOnItemLongClickListener(new OnItemLongClickListener() {
		//
		// @Override
		// public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
		// int arg2, long arg3) {
		// showToast("changan");
		// return false;
		// }
		// });
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				showToast("p=" + position);
				switch (position) {
				case 3:
					goActivity(LehuigoHomeActvity.class, null);
					break;
				case 4:
					goActivity(GoodsSearchActivity.class, null);
					break;
				case 5:
					goActivity(InfoListActivity.class, null);

					break;
				case 6:
					goActivity(ShareActivity.class, null);
					
					break;
				case 7:
					goActivity(SharedActivity.class, null);
					
					break;

				default:
					break;
				}

			}
		});
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
