package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.Goods;

public class BidNoticeListviewAdapter extends BaseCacheListAdapter<Goods> {

	public BidNoticeListviewAdapter(Context context) {
		super(context);
	}

	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_lview_bid_notice, null);
//			holder.image_item = (ImageView) convertView
//					.findViewById(R.id.image_item);
			holder.tv_item_name = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			holder.tv_item_describe = (TextView) convertView
					.findViewById(R.id.tv_item_describe);
			holder.tv_item_type = (TextView) convertView
					.findViewById(R.id.tv_item_type);
			holder.tv_item_time = (TextView) convertView
					.findViewById(R.id.tv_item_time);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		// holder.name.setText(getItem(position).getTitle());
		// holder.describe.setText(getItem(position).getContent());
		// holder.currentPrice.setText("￥"
		// +getItem(position).getCurrentPrice());
		// holder.originalPrice.setText("￥" + getItem(position).getOldPrice());
		// holder.originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		// setCacheImage(holder.imageView,
		// getItem(position).getImgageUrlsmall(),
		// R.drawable.loading_bg_img_item);

		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
//		ImageView image_item;
		TextView tv_item_name;
		TextView tv_item_describe;
		TextView tv_item_type;
		TextView tv_item_time;
	}

}
