package com.mobile.meishang.model.bean;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class MessageLatest {
	private String imageUrl;
	private String title;
	private String goodsId;
	private String more;
	private List<Coupons> mList;

	public MessageLatest() {

	}

	public MessageLatest(JSONObject json) throws JSONException {
		imageUrl = FunctionUtil.getJsonStrValue(json, "");
		title = FunctionUtil.getJsonStrValue(json, "");
		goodsId = FunctionUtil.getJsonStrValue(json, "");
		more = FunctionUtil.getJsonStrValue(json, "");
		if (json.has("")) {
			JSONArray jsonArray = json.getJSONArray("");
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				mList.add(new Coupons(jsonArray.getJSONObject(i)));
			}
		}
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public String getMore() {
		return more;
	}

	public List<Coupons> getmList() {
		return mList;
	}

}
