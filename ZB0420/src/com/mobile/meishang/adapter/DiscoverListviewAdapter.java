package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.Goods;

public class DiscoverListviewAdapter extends BaseCacheListAdapter<Goods> {

	public DiscoverListviewAdapter(Context context) {
		super(context);
	}

	@Override
	public int getCount() {
		return 15;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_listview_discover,
					null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.name = (TextView) convertView.findViewById(R.id.item_name);
			holder.describe = (TextView) convertView
					.findViewById(R.id.item_describe);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		// holder.name.setText(getItem(position).getTitle());
		// holder.describe.setText(getItem(position).getContent());
		// setCacheImage(holder.imageView,
		// getItem(position).getImgageUrlsmall(),
		// R.drawable.loading_bg_img_item);

		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		ImageView imageView;
		TextView name;
		TextView describe;
	}

}
