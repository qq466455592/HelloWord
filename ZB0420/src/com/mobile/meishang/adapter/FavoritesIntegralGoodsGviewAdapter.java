package com.mobile.meishang.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.CombineListItem;

public class FavoritesIntegralGoodsGviewAdapter extends
		BaseCacheListAdapter<CombineListItem> {
	private boolean isEdit;
	private List<Integer> checkPositions;

	public FavoritesIntegralGoodsGviewAdapter(Context context) {
		super(context);
		checkPositions = new ArrayList<Integer>();
	}

	@Override
	public int getCount() {
		return 15;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(
					R.layout.item_gview_favorites_integral_goods, null);
			holder.llayout_item = (LinearLayout) convertView
					.findViewById(R.id.llayout_item);
			holder.image_item = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.tv_item_name = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			holder.tv_item_price = (TextView) convertView
					.findViewById(R.id.tv_item_price);
			holder.tv_item_integral = (TextView) convertView
					.findViewById(R.id.tv_item_integral);
			holder.flayout_item = (FrameLayout) convertView
					.findViewById(R.id.flayout_item);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		// setCacheImage(holder.image, getItem(position).getImgageUrlBig(),
		// R.drawable.loading_bg_img165);
		// holder.title.setText(getItem(position).getTitle());
		if (isEdit) {
			if (checkPositions.contains(position)) {
				holder.flayout_item.setVisibility(View.VISIBLE);
			}else {
				holder.flayout_item.setVisibility(View.GONE);
			}
			holder.llayout_item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(mContext, "编辑" + position,
							Toast.LENGTH_SHORT).show();
					holder.flayout_item.setVisibility(View.VISIBLE);
					checkPositions.add(position);

				}
			});
			holder.flayout_item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (holder.flayout_item.isShown()) {
						holder.flayout_item.setVisibility(View.INVISIBLE);
						if (checkPositions.contains(position)) {
							checkPositions.remove(checkPositions
									.indexOf(position));
						}
					} else {
						holder.flayout_item.setVisibility(View.VISIBLE);
						checkPositions.add(position);

					}
				}
			});

		} else {
			holder.flayout_item.setVisibility(View.GONE);
			holder.llayout_item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(mContext, "未编辑" + position,
							Toast.LENGTH_SHORT).show();

				}
			});
		}

		return convertView;
	}

	static class Holder {
		LinearLayout llayout_item;
		ImageView image_item;
		TextView tv_item_name;
		TextView tv_item_price;
		TextView tv_item_integral;
		FrameLayout flayout_item;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public List<Integer> getCheckPositions() {
		return checkPositions;
	}

}
