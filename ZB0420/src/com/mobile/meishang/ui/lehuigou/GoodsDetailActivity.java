package com.mobile.meishang.ui.lehuigou;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.AdvertisingGalleryAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.AdvertisingGalleryRequest;
import com.mobile.meishang.core.request.GoodsDetailRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.AdvertisingGallery;
import com.mobile.meishang.model.bean.AdvertisingGalleryList;
import com.mobile.meishang.model.bean.Goods;
import com.mobile.meishang.ui.ad.AdvertisingListActivity;
import com.mobile.meishang.ui.shopping.ShoppingCarListActivity;
import com.mobile.meishang.utils.view.AdGallery;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;

public class GoodsDetailActivity extends MActivity implements ExceptionHandler,
		LoadEvent {
	private AdGallery mAdGallery;
	private LinearLayout mAdDotLayout;
	private ImageView[] dotHolder;
	private AdvertisingGalleryAdapter mAdvertisingAdapter;
	private List<AdvertisingGallery> mAdvertisings;
	private final int ADVREFRESH = 1;
	private int selectedPosition = 0;
	private int realPosition = 0;
	private int galleryImgNum;
	private int refreshTime = 2000;
	private RefreshAdvRun mRefreshAdvRun;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case ADVREFRESH:
				mAdGallery.setSelection(selectedPosition++);
				mHandler.postDelayed(mRefreshAdvRun, refreshTime);
				break;
			default:
				break;
			}
		}
	};
	//
	private LoadingView mLoadingView;
	private ImageView mImageView;
	private TextView mName;
	private Goods mGoods;
	private Bundle mBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_detail);
		mAdGallery = (AdGallery) findViewById(R.id.ad_gallery);
		mAdGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				realPosition = position % galleryImgNum;
				selectedPosition = position;
				for (int i = 0; i < galleryImgNum; i++) {
					if (i == realPosition) {
						dotHolder[i]
								.setBackgroundResource(R.drawable.banner_round_select);
					} else {
						dotHolder[i]
								.setBackgroundResource(R.drawable.banner_round_normal);
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		mAdGallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> paramAdapterView,
					View paramView, int paramInt, long paramLong) {
				AdvertisingGallery advertising = mAdvertisings
						.get(realPosition);
				Bundle bundle = new Bundle();
				bundle.putString("name", advertising.getName());
				bundle.putString("actid", advertising.getActid());
				goActivity(AdvertisingListActivity.class, bundle);
				// goActivity(AdvertisingExpandbleActivity.class, bundle);
			}
		});
		mAdGallery.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mHandler.removeCallbacks(mRefreshAdvRun);
					break;
				case MotionEvent.ACTION_UP:
					mHandler.postDelayed(mRefreshAdvRun, refreshTime);
					break;
				}
				return false;
			}
		});

		mAdDotLayout = (LinearLayout) findViewById(R.id.ad_dot_llayout);
		mAdvertisingAdapter = new AdvertisingGalleryAdapter(this);
		mAdGallery.setAdapter(mAdvertisingAdapter);
		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		// ImageView collectImage = (ImageView) findViewById(R.id.top_collect);
		// collectImage.setVisibility(View.VISIBLE);
		// TextView title = (TextView) findViewById(R.id.top_name);
		// title.setText(R.string.goods_detail);
		// title.setVisibility(View.VISIBLE);
		// mLoadingView = (LoadingView) findViewById(R.id.loading);
		// mLoadingView.setLoadEvent(this);
		// mImageView = (ImageView) findViewById(R.id.image);
		// mName = (TextView) findViewById(R.id.tv_name);
		// mBundle = getIntent().getBundleExtra("bundle");
		// getSupportLoaderManager().initLoader(RequestDistribute.GOODS_DETAILS,
		// mBundle, new GoodsDetailRequest(this));
		mBundle = new Bundle();
		mBundle.putString("label", "limitBuy");
		getSupportLoaderManager().initLoader(
				RequestDistribute.ADVERTISING_GALLERY_FLASH_SALE, mBundle,
				new AdvertisingGalleryRequest(this));
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

	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.top_collect:
			// if (TextUtils.isEmpty(MApplication.getSessionId())) {
			// goActivity(LoginActivity.class, null);
			// } else {
			// getSupportLoaderManager().restartLoader(
			// RequestDistribute.FAVORITES_ADD, mBundle,
			// new FavoritesAddRequest(GoodsDetailActivity.this));
			// }

			break;
		case R.id.image:
			// goActivity(GoodsImageShowActivity.class, null);
			break;
		case R.id.tv_share:
			// 首先在您的Activity中添加如下成员变量
			final UMSocialService mController = UMServiceFactory
					.getUMSocialService("com.umeng.share");
			mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN,
					SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ,
					SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT,
					SHARE_MEDIA.DOUBAN, SHARE_MEDIA.RENREN);
			mController.openShare(this, false);

			// 设置分享内容
			mController
					.setShareContent("xxxxxxxxxxxxxxxxxxxxxx，http://www.test.com/social");
			// 设置分享图片, 参数2为图片的url地址
			mController
					.setShareMedia(new UMImage(this,
							"http://103.242.168.154:9001/BCLife/staffPhoto/goods/5.png"));
			break;
		case R.id.flayout_immediately_change:
			goActivity(ShoppingCarListActivity.class, null);
			break;

		default:
			break;
		}
	}

	@Override
	public void updateUI(int identity, Object data) {
		// mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.ADVERTISING_GALLERY_FLASH_SALE:
			AdvertisingGalleryList advertisingList = (AdvertisingGalleryList) data;
			mAdvertisings = advertisingList.getList();
			initAdvPicture();
			break;
		case RequestDistribute.GOODS_DETAILS:
			mGoods = (Goods) data;
			// mBundle.putString("endTime", mGoods.getEndTime());
			// mImageWorker.setLoadingImage(R.drawable.loading_bg_img245);
			// mImageWorker.loadImage(mGoods.getImageUrl(), mImageView);
			// mName.setText(mGoods.getName());
			// mUseRange.setText(mGoods.getUseRange());
			// mBooking.setText(mGoods.getIsBooking());
			// mTel.setText(mGoods.getTel());
			// mAddress.setText(mGoods.getAddress());
			// mContent.setText(Html.fromHtml(mGoods.getDescribe()));
			break;
		case RequestDistribute.FAVORITES_ADD:
			// RequestResponseInfo requestResponseInfo = (RequestResponseInfo)
			// data;
			// if ("".equals(requestResponseInfo.getErrorCode())) {
			// // LeShiHuiApplication.setSessionId(requestResponseInfo
			// // .getSessionId());
			// // Intent intent = new Intent();
			// // intent.putExtra("bundle", mBundle);
			// // setResult(1, intent);
			// // finish();
			// showToast(requestResponseInfo.getState());
			// } else {
			// if ("1".equals(requestResponseInfo.getErrorCode())) {
			//
			// goActivity(LoginActivity.class, null);
			// } else {
			// showToast(requestResponseInfo.getErrorMessage());
			// }
			// }
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
				if (identity == RequestDistribute.GOODS_DETAILS) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		getSupportLoaderManager().restartLoader(
				RequestDistribute.GOODS_DETAILS, mBundle,
				new GoodsDetailRequest(this));

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void initAdvPicture() {
		if (mAdvertisings != null && mAdvertisings.size() > 0) {
			galleryImgNum = mAdvertisings.size();
			mAdvertisingAdapter.clear();
			mAdvertisingAdapter.addAll(mAdvertisings);
			mAdvertisingAdapter.setImagelength(galleryImgNum);
			mAdvertisingAdapter.notifyDataSetChanged();
			mAdGallery.setSelection(100 * galleryImgNum);
			selectedPosition = 100 * galleryImgNum;
			mAdDotLayout.removeAllViews();
			dotHolder = new ImageView[galleryImgNum];
			for (int i = 0; i < galleryImgNum; i++) {
				dotHolder[i] = new ImageView(this);
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				layoutParams.setMargins(5, 0, 0, 0);
				if (i == 0) {
					dotHolder[i]
							.setBackgroundResource(R.drawable.banner_round_select);
				} else {
					dotHolder[i]
							.setBackgroundResource(R.drawable.banner_round_normal);
				}
				mAdDotLayout.addView(dotHolder[i], layoutParams);
			}

			if (mRefreshAdvRun == null) {
				mRefreshAdvRun = new RefreshAdvRun();
			} else {
				mHandler.removeCallbacks(mRefreshAdvRun);
			}
			mHandler.postDelayed(mRefreshAdvRun, refreshTime);

		}
	}

	private class RefreshAdvRun implements Runnable {

		@Override
		public void run() {
			mHandler.sendEmptyMessage(ADVREFRESH);
		}
	}
}
