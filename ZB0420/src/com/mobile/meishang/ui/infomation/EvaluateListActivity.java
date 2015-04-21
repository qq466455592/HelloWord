package com.mobile.meishang.ui.infomation;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.EvaluateListAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.model.bean.TravelNotesEvaluate;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;

public class EvaluateListActivity extends MActivity implements
		ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private ListView mListView;
	private EvaluateListAdapter listAdapter;
	private List<TravelNotesEvaluate> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("评论");
		title.setVisibility(View.VISIBLE);
		TextView publish = (TextView) findViewById(R.id.tv_top_right);
		publish.setText("发表评论");
		publish.setVisibility(View.VISIBLE);
		publish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
//				goActivity(EvaluatePublishActivity.class, bundle);
			}
		});
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
		mListView = (ListView) findViewById(R.id.listview);
		listAdapter = new EvaluateListAdapter(this);
		mListView.setAdapter(listAdapter);
		// list = new ArrayList<TravelNotesEvaluate>();
		// for (int i = 0; i < 5; i++) {
		// list.add(new TravelNotesEvaluate("" + i));
		// }
		// listAdapter.addAll(list);
		// mListView.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// // goActivity(EvaluateDetailActivity.class, null);
		//
		// }
		// });
		// listAdapter.notifyDataSetChanged();

	}

	public void onclick(View v) {
		super.onclick(v);
	}

	@Override
	public void retryAgain(View v) {

	}
}
