package com.mobile.meishang.ui.city;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.CityListViewAdapter;
import com.mobile.meishang.config.Constants;
import com.mobile.meishang.core.request.CityListRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.City;
import com.mobile.meishang.utils.view.MyLetterListView;
import com.mobile.meishang.utils.view.MyLetterListView.OnTouchingLetterChangedListener;
import com.umeng.analytics.MobclickAgent;

public class CityListActivity extends MActivity implements OnClickListener {
	private ListView mListView;
	private List<City> cities;
	private CityListViewAdapter mListViewAdapter;
	private TextView overlay;// 提示字母
	private MyLetterListView letterListView;// 字母列表
	private HashMap<String, Integer> alphaIndexer; // 存放存在的汉语拼音首字母和与之对应的列表位置
	// private String[] sections; // 存放存在的汉语拼音首字母
	private OverlayThread overlayThread; // 浮现字母
	// 刷新界面
	private Handler handler;
	// 底层组件，添加浮现字母
	private WindowManager mWindowManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_list);
		findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("城市选择");
		title.setVisibility(View.VISIBLE);
		mListView = (ListView) findViewById(R.id.listview);
		letterListView = (MyLetterListView) findViewById(R.id.city_list_Letter);
		letterListView
				.setOnTouchingLetterChangedListener(new LetterListViewListener());
		handler = new Handler();
		overlayThread = new OverlayThread();
		initOverlay();
		LayoutInflater inflater = LayoutInflater.from(getApplication());
		View headView = inflater.inflate(R.layout.citylist_headview, null);
		mListViewAdapter = new CityListViewAdapter(this);
		mListView.addHeaderView(headView);
		mListView.setAdapter(mListViewAdapter);
		mListView.setOnScrollListener(scrollListener);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				position--;
				// showToast(cities.get(position).getCityName());
				MApplication
						.getInstance()
						.getmConfig()
						.putPreferencesVal(Constants.CITY_NAME,
								cities.get(position).getCityName());
				MApplication
						.getInstance()
						.getmConfig()
						.putPreferencesVal(Constants.CITY_CODE,
								cities.get(position).getCityCode());
				finish();
			}
		});
		getSupportLoaderManager().initLoader(RequestDistribute.CITY_LIST, null,
				new CityListRequest(this));
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
	protected void onDestroy() {
		super.onDestroy();
		if (mWindowManager != null) {
			mWindowManager.removeView(overlay);
		}
	}

	@Override
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		if (data != null) {
			switch (identity) {
			case RequestDistribute.CITY_LIST:
				cities = (List<City>) data;
				int length = cities.size();
				if (length > 0) {
					alphaIndexer = new HashMap<String, Integer>();
					alphaIndexer.put("热门", 0);
					for (int i = 0; i < length; i++) {
						String currentStr = cities.get(i).getCityAlpha();
						// 上一个汉语拼音首字母，如果不存在为“ ”
						String previewStr = (i - 1) >= 0 ? cities.get(i - 1)
								.getCityAlpha() : " ";
						if (!previewStr.equals(currentStr)) {
							String name = currentStr;
							alphaIndexer.put(name, i + 1);
						}
					}
				}
				mListViewAdapter.addAll(cities);
				mListViewAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
		}
	}

	@Override
	public void onClick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.transparent_flayout_top:
			finish();
			break;
		case R.id.transparent_flayout_bottom:
			finish();
			break;
		case R.id.city_change_llayout:
			finish();
			break;

		default:
			break;
		}
	}

	/**
	 * 初始化汉语拼音首字母弹出提示框
	 */
	private void initOverlay() {
		try {
			LayoutInflater inflater = LayoutInflater.from(this);
			overlay = (TextView) inflater.inflate(R.layout.citylist_overlay,
					null);
			overlay.setVisibility(View.INVISIBLE);
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_APPLICATION,
					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
							| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
					PixelFormat.TRANSLUCENT);
			mWindowManager = (WindowManager) this
					.getSystemService(Context.WINDOW_SERVICE);
			mWindowManager.addView(overlay, lp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 首字母提示
	 * 
	 * @param nameInitials
	 */
	private void showTipsLetter(final String nameInitials) {
		if (!"0".equals(nameInitials)) {
			overlay.setText(nameInitials);
			overlay.setVisibility(View.VISIBLE);
			handler.removeCallbacks(overlayThread);
			// 延迟一秒后执行，让overlay为不可见
			handler.postDelayed(overlayThread, 1500);
		}
	}

	/**
	 * 设置overlay不可见
	 * 
	 * @author zhangbin
	 * 
	 */
	private class OverlayThread implements Runnable {
		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}
	}

	/**
	 * 
	 * LetterListViewListener
	 * 
	 * @Description: 字母单击事件
	 * 
	 */
	private class LetterListViewListener implements
			OnTouchingLetterChangedListener {
		@Override
		public void onTouchingLetterChanged(final String s) {
			if (alphaIndexer != null && alphaIndexer.get(s) != null) {
				int position = alphaIndexer.get(s);
				mListView.setSelection(position);
				// final String nameInitials = sections[position];
				// showTipsLetter(nameInitials);
				showTipsLetter(s);
			}
		}
	}

	/**
	 * 滑动展示的字母
	 */
	OnScrollListener scrollListener = new OnScrollListener() {
		@Override
		public void onScroll(AbsListView view, final int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if (cities != null && cities.size() > 0) {
				final String nameInitials = cities.get(firstVisibleItem)
						.getCityAlpha();
				if (firstVisibleItem != 0
						&& !TextUtils.isEmpty(nameInitials.trim())) {
					showTipsLetter(nameInitials);
				}
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {

		}

	};
}
