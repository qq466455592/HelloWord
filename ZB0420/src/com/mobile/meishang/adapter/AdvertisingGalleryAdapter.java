package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.AdvertisingGallery;
import com.mobile.meishang.utils.FunctionUtil;

@SuppressWarnings("deprecation")
public class AdvertisingGalleryAdapter extends BaseCacheListAdapter<AdvertisingGallery> {
	private Context mContext;
	private int imgWidth;
	private int imgHeight;
	private int imagelength;

	/**
	 * 
	 * @param context
	 */
	public AdvertisingGalleryAdapter(Context context) {
		super(context);
		mContext = context;
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		// imgWidth = display.getWidth() - FunctionUtil.dip2px(context, 350);
		// imgHeight = (int) (imgWidth * (40 / 120f));
		imgWidth = (int) (display.getWidth() - FunctionUtil.dip2px(context, 0));
		imgHeight = (int) (imgWidth * 0.3828);
	}

	@Override
	public int getCount() {
		return (imagelength == 0) ? 0 : Integer.MAX_VALUE;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageview = null;
		if (imageview == null) {
			imageview = new ImageView(mContext);
			imageview.setLayoutParams(new Gallery.LayoutParams(imgWidth,
					imgHeight));
			imageview.setScaleType(ScaleType.FIT_XY);
			if (list != null && list.size() > 0) {
				setCacheImage(imageview, getItem(position % imagelength)
						.getImgeUrl(), R.drawable.loading_bg_img245);
			} else {
				setCacheImage(imageview, "", R.drawable.loading_bg_img245);
			}

		}

		return imageview;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public void setImagelength(int imagelength) {
		this.imagelength = imagelength;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

}
