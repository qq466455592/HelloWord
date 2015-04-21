package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.TravelNotesEvaluate;

public class EvaluateListAdapter extends
		BaseCacheListAdapter<TravelNotesEvaluate> {

	public EvaluateListAdapter(Context context) {
		super(context);

	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(
					R.layout.item_lview_evaluate, null);
			holder.image_item = (ImageView) convertView
					.findViewById(R.id.image_item);
			holder.tv_item_name = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			holder.tv_item_time = (TextView) convertView
					.findViewById(R.id.tv_item_time);
			holder.tv_item_praise_quantity = (TextView) convertView
					.findViewById(R.id.tv_item_praise_quantity);
			holder.tv_item_content = (TextView) convertView
					.findViewById(R.id.tv_item_content);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		// holder.item_tv_title.setText(getItem(position).getName());
		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		ImageView image_item;
		TextView tv_item_name;
		TextView tv_item_time;
		TextView tv_item_praise_quantity;
		TextView tv_item_content;
	}

}
