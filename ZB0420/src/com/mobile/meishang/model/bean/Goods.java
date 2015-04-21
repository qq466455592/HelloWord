package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Goods extends Head implements Parcelable {
	// {
	// "id": 1,
	// "actid": 1,
	// "goodsid": 1,
	// "code": "0000001",
	// "name": "爱西西里巧克力冰激凌1",
	// "lable": null,
	// "logo": "http://103.242.168.154:9001/BCLife/staffPhoto/goods/1.png",
	// "status": 1,
	// "detailUrl": "http://103.242.168.154:9001/BCLife/jsp/act/bus_goods1.jsp",
	// "isDeleted": 1,
	// "createdBy": null,
	// "createdTime": null,
	// "updatedBy": null,
	// "updatedTime": null,
	// "reamrk1": null,
	// "remark2": null,
	// "remark3": null
	// },

	private String goodsid;
	private String actid;
	private String code;
	private String logo;
	private String name;
	private String desp;
	private String price;
	private String price1;
	private String detailUrl;

	public Goods() {
	}

	public Goods(Parcel in) {
		goodsid = in.toString();
		actid = in.toString();
		code = in.toString();
		logo = in.toString();
		name = in.toString();
		desp = in.toString();
		price = in.toString();
		price1 = in.toString();
		detailUrl = in.toString();
	}

	public Goods(JSONObject json) throws JSONException {
		goodsid = getJsonStrValue(json, "goodsid");
		actid = getJsonStrValue(json, "actid");
		code = getJsonStrValue(json, "code");
		logo = getJsonStrValue(json, "logo");
		name = getJsonStrValue(json, "name");
		desp = getJsonStrValue(json, "desp");
		price = getJsonStrValue(json, "price");
		price1 = getJsonStrValue(json, "price1");
		detailUrl = getJsonStrValue(json, "detailUrl");
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(goodsid);
		dest.writeString(actid);
		dest.writeString(code);
		dest.writeString(logo);
		dest.writeString(name);
		dest.writeString(desp);
		dest.writeString(price);
		dest.writeString(price1);
		dest.writeString(detailUrl);

	}

	public static final Parcelable.Creator<Goods> CREATOR = new Creator<Goods>() {

		@Override
		public Goods[] newArray(int size) {
			return new Goods[size];
		}

		@Override
		public Goods createFromParcel(Parcel source) {
			return new Goods(source);
		}
	};

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getActid() {
		return actid;
	}

	public void setActid(String actid) {
		this.actid = actid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

}
