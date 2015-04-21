package com.mobile.meishang.ui.city;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.CityAreaGridViewAdapter;
import com.mobile.meishang.config.Constants;
import com.mobile.meishang.model.bean.Area;
import com.umeng.analytics.MobclickAgent;

public class AreaChoiceActivity extends MActivity implements
		OnClickListener {

	private FrameLayout transparentLayoutTop;
	private FrameLayout transparentLayoutBottom;
	private GridView mGridView;
	private LinearLayout cityChangeLLayout;
	private TextView cityName;
	private CityAreaGridViewAdapter mGridViewAdapter;
	private List<Area> areas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cityarea_choice);
		transparentLayoutTop = (FrameLayout) findViewById(R.id.transparent_flayout_top);
		transparentLayoutTop.setOnClickListener(this);
		transparentLayoutBottom = (FrameLayout) findViewById(R.id.transparent_flayout_bottom);
		transparentLayoutBottom.setOnClickListener(this);
		cityChangeLLayout = (LinearLayout) findViewById(R.id.city_change_llayout);
		cityChangeLLayout.setOnClickListener(this);
		cityName = (TextView) findViewById(R.id.city_name);
		cityName.setText(MApplication.getInstance().getmConfig()
				.getPreferencesVal(Constants.CITY_NAME, "北京"));
		mGridView = (GridView) findViewById(R.id.gridview);
		mGridViewAdapter = new CityAreaGridViewAdapter(this);
		mGridView.setAdapter(mGridViewAdapter);
		areas = new ArrayList<Area>();
		for (int i = 0; i < 12; i++) {
			areas.add(new Area("城市" + i));
		}
		mGridViewAdapter.addAll(areas);
		mGridViewAdapter.notifyDataSetChanged();
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
	public void onClick(View v) {
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
}
