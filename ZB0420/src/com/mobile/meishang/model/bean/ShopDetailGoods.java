package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ShopDetailGoods extends Goods {

	public ShopDetailGoods() {
	}

	public ShopDetailGoods(JSONObject json) throws JSONException {
		super(json);
	}

}
