package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.utils.FunctionUtil;

public class CertificateItem implements Parcelable {
	private String goodsId;
	private String goodsName;
	private String code;
	private String deadlines;// 截止日期
	private String remainingDays;// 剩余天数
	private String imgageUrl;

	public CertificateItem() {
	}

	public CertificateItem(Parcel in) {
		goodsId = in.readString();
		goodsName = in.readString();
		code = in.readString();
		deadlines = in.readString();
		remainingDays = in.readString();
		imgageUrl = in.readString();
	}

	public CertificateItem(JSONObject json) throws JSONException {
		goodsId = FunctionUtil.getJsonStrValue(json, "goods_id");
		goodsName = FunctionUtil.getJsonStrValue(json, "goods_name");
		code = FunctionUtil.getJsonStrValue(json, "code");
		deadlines = FunctionUtil.getJsonStrValue(json, "enddate");
		remainingDays = FunctionUtil.getJsonStrValue(json, "days");
		imgageUrl = MApplication.getInstance().getmConfig().urlImage
				+ FunctionUtil.getJsonStrValue(json, "goods_thumb");
	}

	public String getGoodsId() {
		return goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getCode() {
		return code;
	}

	public String getDeadlines() {
		return deadlines;
	}

	public String getRemainingDays() {
		return remainingDays;
	}

	public String getImgageUrl() {
		return imgageUrl;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(goodsId);
		dest.writeString(goodsName);
		dest.writeString(code);
		dest.writeString(deadlines);
		dest.writeString(remainingDays);
		dest.writeString(imgageUrl);
	}

	public static final Parcelable.Creator<CertificateItem> CREATOR = new Creator<CertificateItem>() {

		@Override
		public CertificateItem[] newArray(int size) {
			return new CertificateItem[size];
		}

		@Override
		public CertificateItem createFromParcel(Parcel source) {
			return new CertificateItem(source);
		}
	};
}
