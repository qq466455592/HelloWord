package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class Coupons {

	private String name;
	private String describe;
	private String currentPrice;
	private String price;
	private String saleQuantity;
	private String isNewProduct;
	private String imgUrl;

	public Coupons() {
	}

	public Coupons(JSONObject json) throws JSONException {
		name = FunctionUtil.getJsonStrValue(json, "");
		describe = FunctionUtil.getJsonStrValue(json, "");
		currentPrice = FunctionUtil.getJsonStrValue(json, "");
		price = FunctionUtil.getJsonStrValue(json, "");
		saleQuantity = FunctionUtil.getJsonStrValue(json, "");
		isNewProduct = FunctionUtil.getJsonStrValue(json, "");
		imgUrl = FunctionUtil.getJsonStrValue(json, "");
	}

	public String getName() {
		return name;
	}

	public String getDescribe() {
		return describe;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public String getPrice() {
		return price;
	}

	public String getSaleQuantity() {
		return saleQuantity;
	}

	public String getIsNewProduct() {
		return isNewProduct;
	}

	public String getImgUrl() {
		return imgUrl;
	}

}
