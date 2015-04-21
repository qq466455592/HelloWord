package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.City;

public class CityListViewAdapter extends BaseListAdapter<City> {
	private Context mContext;
	private LayoutInflater inflater;

	public CityListViewAdapter(Context context) {
		mContext = context;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView = null;
		if (convertView == null) {
			holderView = new HolderView();
			convertView = inflater.inflate(R.layout.item_listview_city_list,
					null);
			holderView.alpha = (TextView) convertView.findViewById(R.id.alpha);
			holderView.cityName = (TextView) convertView
					.findViewById(R.id.city_name);
			convertView.setTag(holderView);

		} else {
			holderView = (HolderView) convertView.getTag();
		}

		String tempCityAlpha = getItem(position).getCityAlpha();
		String mCityAlpha = (position - 1) >= 0 ? getItem(position - 1)
				.getCityAlpha() : " ";
		if (mCityAlpha.equals(tempCityAlpha)) {
			holderView.alpha.setVisibility(View.GONE);
		} else {
			holderView.alpha.setText(tempCityAlpha);
			holderView.alpha.setVisibility(View.VISIBLE);
		}
		holderView.cityName.setText(getItem(position).getCityName());
		return convertView;
	}

	static class HolderView {
		TextView alpha;
		TextView cityName;
	}
}
