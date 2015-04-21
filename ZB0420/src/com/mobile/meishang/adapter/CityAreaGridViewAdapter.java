package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.Area;

public class CityAreaGridViewAdapter extends BaseListAdapter<Area> {
	private Context mContext;
	private LayoutInflater inflater;

	public CityAreaGridViewAdapter(Context context) {
		mContext = context;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView = null;
		if (convertView == null) {
			holderView = new HolderView();
			convertView = inflater.inflate(R.layout.item_gridview_city_choice,
					null);
			holderView.areaName = (TextView) convertView
					.findViewById(R.id.item_name);
			convertView.setTag(holderView);

		} else {
			holderView = (HolderView) convertView.getTag();
		}
		holderView.areaName.setText(getItem(position).getAreaName());
		return convertView;
	}

	static class HolderView {
		TextView areaName;
	}
}
