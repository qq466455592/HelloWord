package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.Goods;

public class SignInListviewAdapter extends BaseCacheListAdapter<Goods> {

	public SignInListviewAdapter(Context context) {
		super(context);
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_lview_sign_in, null);
			holder.item_image = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.item_tv_name = (TextView) convertView
					.findViewById(R.id.item_tv_name);
			holder.item_tv_describe = (TextView) convertView
					.findViewById(R.id.item_tv_describe);
			holder.item_tv_price = (TextView) convertView
					.findViewById(R.id.item_tv_price);
			holder.item_tv_award = (TextView) convertView
					.findViewById(R.id.item_tv_award);
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
		ImageView item_image;
		TextView item_tv_name;
		TextView item_tv_describe;
		TextView item_tv_price;
		TextView item_tv_award;
	}

}
