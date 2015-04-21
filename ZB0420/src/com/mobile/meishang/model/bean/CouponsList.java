package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class CouponsList  {

	private String PageNumber;
	private String PageCount;
	private List<Coupons> mList;

	public CouponsList() {
	}

	public CouponsList(JSONObject json) throws JSONException {
		PageNumber = FunctionUtil.getJsonStrValue(json, "");
		PageCount = FunctionUtil.getJsonStrValue(json, "");
		JSONArray jsonArray = json.getJSONArray("");
		mList = new ArrayList<Coupons>();
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			mList.add(new Coupons(jsonObject));
		}
	}

	public String getPageNumber() {
		return PageNumber;
	}

	public String getPageCount() {
		return PageCount;
	}

	public List<Coupons> getmList() {
		return mList;
	}

}
