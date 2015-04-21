package com.mobile.meishang.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.ui.baidu.BaiduLocation;
import com.mobile.meishang.ui.home.fragments.DiscoverFragment;
import com.mobile.meishang.ui.home.fragments.FlashsaleFragment;
import com.mobile.meishang.ui.home.fragments.HomeFragment;
import com.mobile.meishang.ui.home.fragments.MeFragment;
import com.mobile.meishang.ui.home.fragments.SignInFragment;

public class TabActivity extends MActivity {
	// private LinearLayout mCityLayout;
	// private TextView mCityTextView;
	// private TextView mTitleTextView;
	private LinearLayout mTabLayoutA;
	private LinearLayout mTabLayoutB;
	private LinearLayout mTabLayoutC;
	private LinearLayout mTabLayoutD;
	private HomeFragment homeFragment;
	private DiscoverFragment discoverFragment;
	private SignInFragment signInFragment;
	private MeFragment meFragment;
	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		if (savedInstanceState == null) {
			new BaiduLocation(this);
			// new VersionUpdate(this);
			// new LocationNetwork(this);
		}
		// mCityLayout = (LinearLayout) findViewById(R.id.llayout_city);
		// mCityTextView = (TextView) findViewById(R.id.tv_city_name);
		// mTitleTextView = (TextView) findViewById(R.id.tv_title);
		mTabLayoutA = (LinearLayout) findViewById(R.id.flayout_tab_a);
		mTabLayoutB = (LinearLayout) findViewById(R.id.flayout_tab_b);
		mTabLayoutC = (LinearLayout) findViewById(R.id.flayout_tab_c);
		mTabLayoutD = (LinearLayout) findViewById(R.id.flayout_tab_d);
		mFragmentManager = getSupportFragmentManager();
		// mTitleTextView.setText("首页");
		mTabLayoutA.setSelected(true);
		homeFragment = new HomeFragment();
		addFragment(homeFragment);
		showFragment(homeFragment);
		// mTitleTextView.setText("生活");
		// mTabLayoutC.setSelected(true);
		// mLifeFragment = new LifeFragment();
		// addFragment(mLifeFragment);
		// showFragment(mLifeFragment);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case RequestDistribute.LOGIN:
				// mHomeLayout.setSelected(false);
				// mNearbyLayout.setSelected(false);
				// mMyLayout.setSelected(true);
				// if (myFragment == null) {
				// myFragment = new MyFragment();
				// addFragment(myFragment);
				// }
				// showFragment(myFragment);
				break;

			default:
				break;
			}

		}
	}

	public void addFragment(Fragment fragment) {
		mFragmentManager.beginTransaction()
				.add(R.id.flayout_fragment, fragment).commit();
	}

	public void showFragment(Fragment fragment) {
		hideAllFragment();
		mFragmentManager.beginTransaction().show(fragment).commit();
	}

	public void hideFragment(Fragment fragment) {
		mFragmentManager.beginTransaction().hide(fragment).commit();
	}

	public void hideAllFragment() {
		if (homeFragment != null) {
			hideFragment(homeFragment);
		}
		if (discoverFragment != null) {
			hideFragment(discoverFragment);
		}
		if (signInFragment != null) {
			hideFragment(signInFragment);
		}
		if (meFragment != null) {
			hideFragment(meFragment);
		}
	}

	public void showOrHideFragment(Fragment fragment) {
		mFragmentManager.beginTransaction()
				.replace(R.id.flayout_fragment, fragment).commit();
	}

	public void showHomeFragment() {
		this.onclick(mTabLayoutA);
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);

		switch (v.getId()) {

		case R.id.flayout_tab_a:
			// mCityLayout.setVisibility(View.VISIBLE);
			// mTitleTextView.setText("One Day");
			mTabLayoutA.setSelected(true);
			mTabLayoutB.setSelected(false);
			mTabLayoutC.setSelected(false);
			mTabLayoutD.setSelected(false);
			showFragment(homeFragment);

			break;
		case R.id.flayout_tab_b:
			// mCityLayout.setVisibility(View.VISIBLE);
			// mTitleTextView.setText("限时抢");
			mTabLayoutA.setSelected(false);
			mTabLayoutB.setSelected(true);
			mTabLayoutC.setSelected(false);
			mTabLayoutD.setSelected(false);
			if (discoverFragment == null) {
				discoverFragment = new DiscoverFragment();
				addFragment(discoverFragment);
			}
			showFragment(discoverFragment);
			break;
		case R.id.flayout_tab_c:
			// mTitleTextView.setText("生活");
			mTabLayoutA.setSelected(false);
			mTabLayoutB.setSelected(false);
			mTabLayoutC.setSelected(true);
			mTabLayoutD.setSelected(false);
			if (signInFragment == null) {
				signInFragment = new SignInFragment();
				addFragment(signInFragment);
			}
			showFragment(signInFragment);

			break;
		case R.id.flayout_tab_d:
			// if (MApplication.getInstance().checkLogin()) {
			// mCityLayout.setVisibility(View.INVISIBLE);
			// mTitleTextView.setText("我");
			mTabLayoutA.setSelected(false);
			mTabLayoutB.setSelected(false);
			mTabLayoutC.setSelected(false);
			mTabLayoutD.setSelected(true);
			if (meFragment == null) {
				meFragment = new MeFragment();
				addFragment(meFragment);
			}
			showFragment(meFragment);
			// } else {
			// goActivityForResult(LoginActivity.class, null,
			// RequestDistribute.LOGIN);
			// }
			break;
		case R.id.llayout_city:
			// goActivity(CityListActivity.class, null);
			break;
		case R.id.img_scan:
			// goActivity(CaptureActivity.class, null);
			break;
		case R.id.img_edit_pen:
			// goActivity(GoWhereActivity.class, null);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitDialog();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
