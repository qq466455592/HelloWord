package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.GoodsItem;

public class GoodsListviewAdapter extends BaseCacheListAdapter<GoodsItem> {

	public GoodsListviewAdapter(Context context) {
		super(context);
	}

	@Override
	public int getCount() {
		// return super.getCount();
		return 10;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(
					R.layout.item_expand_shopping_home_child, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.title = (TextView) convertView
					.findViewById(R.id.item_tv_title);
			holder.describe = (TextView) convertView
					.findViewById(R.id.item_tv_describe);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		ImageView imageView;
		TextView title;
		TextView describe;
	}

}
