package com.mobile.meishang.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.Goods;

public class ShoppingCarListAdapter extends BaseCacheListAdapter<Goods> {

	private List<Integer> checkPositions;

	public ShoppingCarListAdapter(Context context) {
		super(context);
		checkPositions = new ArrayList<Integer>();

	}

	@Override
	public int getCount() {
		// return super.getCount();
		return 5;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(
					R.layout.item_listview_shopping_car, null);
			holder.llayout_item = (LinearLayout) convertView
					.findViewById(R.id.llayout_item);
			holder.image_item_check_box = (ImageView) convertView
					.findViewById(R.id.image_item_check_box);
			holder.image_item = (ImageView) convertView
					.findViewById(R.id.image_item);
			holder.tv_item_name = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			holder.tv_item_price = (TextView) convertView
					.findViewById(R.id.tv_item_price);
			holder.tv_item_price_b = (TextView) convertView
					.findViewById(R.id.tv_item_price_b);
			holder.tv_item_add = (TextView) convertView
					.findViewById(R.id.tv_item_add);
			holder.tv_item_quantity = (TextView) convertView
					.findViewById(R.id.tv_item_quantity);
			holder.tv_item_sub = (TextView) convertView
					.findViewById(R.id.tv_item_sub);
			holder.llayout_item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (holder.image_item_check_box.isSelected()) {
						holder.image_item_check_box.setSelected(false);
						if (checkPositions.contains(position)) {
							checkPositions.remove(checkPositions
									.indexOf(position));
						}
					} else {
						holder.image_item_check_box.setSelected(true);
						checkPositions.add(position);

					}
				}
			});
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		// holder.name.setText(getItem(position).getTitle());
		// holder.describe.setText(getItem(position).getContent());
		// holder.currentPrice.setText(getItem(position).getCurrentPrice());
		// holder.originalPrice.setText("原价:" +
		// getItem(position).getOldPrice());
		// holder.originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		// if ("1".equals(getItem(position).getIsNew())) {
		// holder.isNewImg.setVisibility(View.VISIBLE);
		// } else {
		// holder.isNewImg.setVisibility(View.GONE);
		//
		// }
		// setCacheImage(holder.imageView,
		// getItem(position).getImgageUrlsmall(),
		// R.drawable.loading_bg_img_item);
		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		LinearLayout llayout_item;
		ImageView image_item_check_box;
		ImageView image_item;
		TextView tv_item_name;
		TextView tv_item_price;
		TextView tv_item_price_b;
		TextView tv_item_add;
		TextView tv_item_quantity;
		TextView tv_item_sub;
	}

	public List<Integer> getCheckPositions() {
		return checkPositions;
	}

}
