package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.mobile.meishang.MApplication;

public class Shop extends Head implements Parcelable {
	private String title;
	private String store_code;
	private String address;
	private String imageUrl;
	private int distance;
	private String merchant_id;

	public Shop() {
	}

	public Shop(Parcel in) {
		title = in.readString();
		store_code = in.readString();
		address = in.readString();
		imageUrl = in.readString();
		merchant_id = in.readString();
	}

	public Shop(JSONObject json) throws JSONException {
		title = getJsonStrValue(json, "title");
		store_code = getJsonStrValue(json, "store_code");
		address = getJsonStrValue(json, "address");
		distance = getJsonIntValue(json, "distance");
		merchant_id = getJsonStrValue(json, "merchant_id");
		imageUrl = getJsonStrValue(json, "merchant_id");
		if (!TextUtils.isEmpty(imageUrl)) {
			imageUrl = MApplication.getInstance().getmConfig().urlRootApi
					+ "/staffPhoto/storeLogo/" + imageUrl + ".jpg";
		}
	}

	public String getTitle() {
		return title;
	}

	public String getAddress() {
		return address;
	}

	public int getDistance() {
		return distance;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getStore_code() {
		return store_code;
	}

	public void setStore_code(String store_code) {
		this.store_code = store_code;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(store_code);
		dest.writeString(address);
		dest.writeString(imageUrl);
		dest.writeString(merchant_id);
	}

	public static final Parcelable.Creator<Shop> CREATOR = new Creator<Shop>() {

		@Override
		public Shop[] newArray(int size) {
			return new Shop[size];
		}

		@Override
		public Shop createFromParcel(Parcel source) {
			return new Shop(source);
		}
	};
}
