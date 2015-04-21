package com.mobile.meishang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.Goods;

public class AdvertisingListviewAdapter extends BaseCacheListAdapter<Goods> {

	public AdvertisingListviewAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_listview_advertising,
					null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.title = (TextView) convertView
					.findViewById(R.id.item_tv_title);
			holder.describe = (TextView) convertView
					.findViewById(R.id.item_tv_describe);
			holder.currentPrice = (TextView) convertView
					.findViewById(R.id.item_tv_current_price);
			holder.originalPrice = (TextView) convertView
					.findViewById(R.id.item_tv_original_price);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		setCacheImage(holder.imageView, getItem(position).getLogo(),
				R.drawable.loading_bg_img_item);
		holder.title.setText(getItem(position).getName());
		holder.describe.setText(getItem(position).getDesp());
		holder.currentPrice.setText("￥" + getItem(position).getPrice1());
		holder.originalPrice.setText("￥" + getItem(position).getPrice());
		holder.originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	private static class Holder {
		ImageView imageView;
		TextView title;
		TextView describe;
		TextView currentPrice;
		TextView originalPrice;
	}

}
