package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public class SelectCinimaItem extends Head implements Parcelable {
	// {
	// "title": "上海17.5影城（闽行乐虹坊店）",
	// "location": [
	// 121.38282675753,
	// 31.18040664152
	// ],
	// "city": "上海市",
	// "create_time": 1422265502,
	// "geotable_id": 92079,
	// "address": "上海市闵行区虹井路288号4楼",
	// "tags": "movie",
	// "province": "上海市",
	// "district": "闵行区",
	// "cinemaid": "476",
	// "merchant_id": "64",
	// "store_code": "64_2583",
	// "tag": "movie",
	// "uid": 634242129,
	// "coord_type": 3,
	// "type": 0,
	// "distance": 3679,
	// "weight": 0
	// }

	private String cinemaid;
	private String title;
	private String address;

	public SelectCinimaItem() {
	}

	public SelectCinimaItem(Parcel in) {

		title = in.readString();
	}

	public SelectCinimaItem(JSONObject json) throws JSONException {
		cinemaid = getJsonStrValue(json, "cinemaid");
		title = getJsonStrValue(json, "title");
		address = getJsonStrValue(json, "address");
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(cinemaid);
		dest.writeString(title);
		dest.writeString(address);

	}

	public static final Parcelable.Creator<SelectCinimaItem> CREATOR = new Creator<SelectCinimaItem>() {

		@Override
		public SelectCinimaItem[] newArray(int size) {
			return new SelectCinimaItem[size];
		}

		@Override
		public SelectCinimaItem createFromParcel(Parcel source) {
			return new SelectCinimaItem(source);
		}
	};

	public String getCinemaid() {
		return cinemaid;
	}

	public String getTitle() {
		return title;
	}

	public String getAddress() {
		return address;
	}

}
