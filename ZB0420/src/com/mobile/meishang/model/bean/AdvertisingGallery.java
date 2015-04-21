package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class AdvertisingGallery extends Head implements Parcelable {
	private String actid;
	private String name;
	private String imgeUrl;

	public AdvertisingGallery() {
	}

	public AdvertisingGallery(JSONObject json) throws JSONException {
		actid = getJsonStrValue(json, "actid");
		name = getJsonStrValue(json, "name");
		imgeUrl = getJsonStrValue(json, "logo");
	}

	public AdvertisingGallery(Parcel in) {
		actid = in.readString();
		name = in.readString();
		imgeUrl = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(actid);
		dest.writeString(name);
		dest.writeString(imgeUrl);
	}

	public static final Parcelable.Creator<AdvertisingGallery> CREATOR = new Creator<AdvertisingGallery>() {

		@Override
		public AdvertisingGallery[] newArray(int size) {
			return new AdvertisingGallery[size];
		}

		@Override
		public AdvertisingGallery createFromParcel(Parcel source) {
			return new AdvertisingGallery(source);
		}
	};

	public String getActid() {
		return actid;
	}

	public void setActid(String actid) {
		this.actid = actid;
	}

	public String getImgeUrl() {
		return imgeUrl;
	}

	public void setImgeUrl(String imgeUrl) {
		this.imgeUrl = imgeUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
