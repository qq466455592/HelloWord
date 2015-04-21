package com.mobile.meishang.ui.home;
//package com.ivpoints.mobile.life.ui.home;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.os.Bundle;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v4.view.ViewPager.OnPageChangeListener;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.ivpoints.mobile.life.MActivity;
//import com.ivpoints.mobile.life.R;
//import com.ivpoints.mobile.life.adapter.ShowBigImageAdapter;
//import com.umeng.analytics.MobclickAgent;
//
//public class GoodsImageShowActivity extends MActivity {
//	// private ShowBigImageAdapter showImageAdapter;// 商品大图适配器
//	private ViewPager mViewPager;
//	private List<View> mViewList;
//	private TextView mPagerSize;
//	private TextView mLabel;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_coupons_image_show);
//		mPagerSize = (TextView) findViewById(R.id.pager_size);
//		mLabel = (TextView) findViewById(R.id.label);
//		mViewPager = (ViewPager) findViewById(R.id.pager);
//		mViewList = new ArrayList<View>();
//		for (int i = 0; i < 5; i++) {
//			LayoutInflater layoutInflater = LayoutInflater.from(this);
//			View view = layoutInflater.inflate(R.layout.view_imageview, null);
//			mViewList.add(view);
//		}
//		mPagerSize.setText(1 + "/" + mViewList.size());
//		MyPagerAdapter myAdapter = new MyPagerAdapter();
//		mViewPager.setAdapter(myAdapter);
//		mViewPager.setCurrentItem(0);
//		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
//
//	}
//
//	@Override
//	public void onResume() {
//		super.onResume();
//		MobclickAgent.onResume(this);
//	}
//
//	@Override
//	public void onPause() {
//		super.onPause();
//		MobclickAgent.onPause(this);
//	}
//
//	private class MyPagerAdapter extends PagerAdapter {
//
//		@Override
//		public void destroyItem(ViewGroup container, int position, Object object) {
//			container.removeView(mViewList.get(position));
//		}
//
//		@Override
//		public Object instantiateItem(ViewGroup container, int position) {
//			container.addView(mViewList.get(position), 0);
//			return mViewList.get(position);
//		}
//
//		@Override
//		public int getCount() {
//			return mViewList.size();
//		}
//
//		@Override
//		public boolean isViewFromObject(View arg0, Object arg1) {
//			return arg0 == arg1;
//		}
//
//	}
//
//	public class MyOnPageChangeListener implements OnPageChangeListener {
//
//		// int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
//		// int two = one * 2;// 页卡1 -> 页卡3 偏移量
//		public void onPageScrollStateChanged(int arg0) {
//		}
//
//		public void onPageScrolled(int arg0, float arg1, int arg2) {
//		}
//
//		public void onPageSelected(int position) {
//			mPagerSize.setText(++position + "/" + mViewList.size());
//			if (position == 1) {
//				mLabel.setText("向右滑动浏览");
//			} else if (position == mViewList.size()) {
//				mLabel.setText("向左滑动浏览");
//			}
//			/*
//			 * 两种方法，这个是一种，下面还有一种，显然这个比较麻烦 Animation animation = null; switch
//			 * (arg0) { case 0: if (currIndex == 1) { animation = new
//			 * TranslateAnimation(one, 0, 0, 0); } else if (currIndex == 2) {
//			 * animation = new TranslateAnimation(two, 0, 0, 0); } break; case
//			 * 1: if (currIndex == 0) { animation = new
//			 * TranslateAnimation(offset, one, 0, 0); } else if (currIndex == 2)
//			 * { animation = new TranslateAnimation(two, one, 0, 0); } break;
//			 * case 2: if (currIndex == 0) { animation = new
//			 * TranslateAnimation(offset, two, 0, 0); } else if (currIndex == 1)
//			 * { animation = new TranslateAnimation(one, two, 0, 0); } break;
//			 * 
//			 * }
//			 */
//			// Animation animation = new TranslateAnimation(one*currIndex,
//			// one*arg0, 0, 0);//显然这个比较简洁，只有一行代码。
//			// currIndex = arg0;
//			// animation.setFillAfter(true);// True:图片停在动画结束位置
//			// animation.setDuration(300);
//			// imageView.startAnimation(animation);
//			// Toast.makeText(WeiBoActivity.this, "您选择了"+
//			// viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();
//		}
//
//	}
//
//}
