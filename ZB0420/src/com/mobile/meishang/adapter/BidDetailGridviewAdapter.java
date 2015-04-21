package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.Goods;

public class BidDetailGridviewAdapter extends BaseListAdapter<Goods> {

	private Context mContext;

	public BidDetailGridviewAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return 6;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_gridview_home, null);
			holder.item_name = (TextView) convertView
					.findViewById(R.id.item_name);
			// holder.item_name.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// Toast.makeText(mContext, "item_tv_title" + position,
			// Toast.LENGTH_SHORT).show();
			// }
			// });
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.item_name.setText("首页");
		holder.item_name
				.setCompoundDrawablesWithIntrinsicBounds(null, mContext
						.getResources().getDrawable(R.drawable.ic_launcher),
						null, null);
		return convertView;
	}

	static class Holder {
		TextView item_name;
	}

}
