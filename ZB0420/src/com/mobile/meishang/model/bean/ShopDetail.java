package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShopDetail extends Head {

	private String shopTel;
	private List<ShopDetailGoods> mList;

	public ShopDetail() {
	}

	public ShopDetail(JSONObject json) throws JSONException {
		super(json);
		JSONObject bodyJsonObject = getJsonObject(json, "body");
		JSONObject storeJsonObject = getJsonObject(bodyJsonObject, "store");
		shopTel = getJsonStrValue(storeJsonObject, "picOphone");
		mList = new ArrayList<ShopDetailGoods>();
		JSONArray goodsArray = getJsonArray(storeJsonObject, "goodsList");
		if (goodsArray != null) {
			int length = goodsArray.length();
			for (int i = 0; i < length; i++) {
				mList.add(new ShopDetailGoods(goodsArray.getJSONObject(i)));
			}
		}
	}

	public String getShopTel() {
		return shopTel;
	}

	public List<ShopDetailGoods> getmList() {
		return mList;
	}

}
