package com.mobile.meishang.ui.favorites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.model.RequestDistribute;

public class FavoritesActivity extends MActivity {
	private TextView tv_top_right;
	private TextView tv_tab_a;
	private TextView tv_tab_b;
	private TextView tv_tab_c;
	private FavoritesIntegralGoodsFragment integralGoodsFragment;
	private FavoritesProjectListFragment projectListFragment;
	private FavoritesInfoListFragment infoListFragment;
	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
		TextView tv_title = (TextView) findViewById(R.id.top_name);
		tv_title.setText("我的收藏");
		tv_top_right = (TextView) findViewById(R.id.tv_top_right);
		tv_top_right.setText("编辑");
		tv_top_right.setVisibility(View.VISIBLE);
		tv_tab_a = (TextView) findViewById(R.id.tv_tab_a);
		tv_tab_b = (TextView) findViewById(R.id.tv_tab_b);
		tv_tab_c = (TextView) findViewById(R.id.tv_tab_c);
		mFragmentManager = getSupportFragmentManager();
		tv_tab_a.setSelected(true);
		integralGoodsFragment = new FavoritesIntegralGoodsFragment();
		addFragment(integralGoodsFragment);
		showFragment(integralGoodsFragment);
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
		if (integralGoodsFragment != null) {
			hideFragment(integralGoodsFragment);
		}
		if (projectListFragment != null) {
			hideFragment(projectListFragment);
		}
		if (infoListFragment != null) {
			hideFragment(infoListFragment);
		}
	}

	public void showOrHideFragment(Fragment fragment) {
		mFragmentManager.beginTransaction()
				.replace(R.id.flayout_fragment, fragment).commit();
	}

	public void showHomeFragment() {
		this.onclick(tv_tab_a);
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);

		switch (v.getId()) {
		case R.id.tv_top_right:
			if ("关闭".equals(tv_top_right.getText().toString())) {
				tv_top_right.setText("编辑");
				if (integralGoodsFragment != null
						&& integralGoodsFragment.isVisible()) {
					integralGoodsFragment.hideDelete();
				}
				if (projectListFragment != null
						&& projectListFragment.isVisible()) {
					showToast("222222222");
				}
				if (infoListFragment != null && infoListFragment.isVisible()) {
					showToast("3333333");
				}
			} else {
				tv_top_right.setText("关闭");
				if (integralGoodsFragment != null
						&& integralGoodsFragment.isVisible()) {
					integralGoodsFragment.showDelete();
					showToast("delete");
				}
				if (projectListFragment != null
						&& projectListFragment.isVisible()) {
					showToast("222222222");
				}
				if (infoListFragment != null && infoListFragment.isVisible()) {
					showToast("3333333");
				}
				
			}

			break;
		case R.id.tv_tab_a:
			tv_tab_a.setSelected(true);
			tv_tab_b.setSelected(false);
			tv_tab_c.setSelected(false);
			showFragment(integralGoodsFragment);
			break;
		case R.id.tv_tab_b:
			tv_tab_a.setSelected(false);
			tv_tab_b.setSelected(true);
			tv_tab_c.setSelected(false);
			if (projectListFragment == null) {
				projectListFragment = new FavoritesProjectListFragment();
				addFragment(projectListFragment);
			}
			showFragment(projectListFragment);
			break;
		case R.id.tv_tab_c:
			tv_tab_a.setSelected(false);
			tv_tab_b.setSelected(false);
			tv_tab_c.setSelected(true);
			if (infoListFragment == null) {
				infoListFragment = new FavoritesInfoListFragment();
				addFragment(infoListFragment);
			}
			showFragment(infoListFragment);

			break;
		default:
			break;
		}
	}

}
