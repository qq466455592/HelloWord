package com.mobile.meishang.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.ui.home.TabActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * @Title:
 * @Description:
 * @Author:12071520
 * @Since:2013-9-30
 * @Version:
 */
public class SlidingAroundGuideActivity extends MActivity {
	private List<View> mViewList;
	private ViewPager myViewPager;
	private MyPagerAdapter myAdapter;
	private int pageNumber;
	private int[] guideImg = { R.drawable.guide_a, R.drawable.guide_b,
			R.drawable.guide_c, R.drawable.guide_d };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.suning.mobile.suningsummer.SuningSummerActivity#onCreate(android.
	 * os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		mViewList = new ArrayList<View>();
		pageNumber = guideImg.length;
		for (int i = 0; i < pageNumber; i++) {
			mViewList.add(generateContentView(i));
		}
		myAdapter = new MyPagerAdapter();
		myViewPager = (ViewPager) findViewById(R.id.view_pager);
		myViewPager.setAdapter(myAdapter);
		myViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
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

	private View generateContentView(int index) {
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundResource(guideImg[index]);
		return imageView;

	}

	private class MyPagerAdapter extends PagerAdapter {
		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mViewList.get(arg1));
		}

		@Override
		public int getCount() {
			return mViewList.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mViewList.get(arg1), 0);
			return mViewList.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object object) {
			return arg0 == (object);
		}

	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		public void onPageScrollStateChanged(int arg0) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageSelected(int position) {
			if (++position == pageNumber) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(2000);
							goActivity(TabActivity.class, null);
							finish();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
			/*
			 * 两种方法，这个是一种，下面还有一种，显然这个比较麻烦 Animation animation = null; switch
			 * (arg0) { case 0: if (currIndex == 1) { animation = new
			 * TranslateAnimation(one, 0, 0, 0); } else if (currIndex == 2) {
			 * animation = new TranslateAnimation(two, 0, 0, 0); } break; case
			 * 1: if (currIndex == 0) { animation = new
			 * TranslateAnimation(offset, one, 0, 0); } else if (currIndex == 2)
			 * { animation = new TranslateAnimation(two, one, 0, 0); } break;
			 * case 2: if (currIndex == 0) { animation = new
			 * TranslateAnimation(offset, two, 0, 0); } else if (currIndex == 1)
			 * { animation = new TranslateAnimation(one, two, 0, 0); } break;
			 * 
			 * }
			 */
			// Animation animation = new TranslateAnimation(one*currIndex,
			// one*arg0, 0, 0);//显然这个比较简洁，只有一行代码。
			// currIndex = arg0;
			// animation.setFillAfter(true);// True:图片停在动画结束位置
			// animation.setDuration(300);
			// imageView.startAnimation(animation);
			// Toast.makeText(WeiBoActivity.this, "您选择了"+
			// viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
