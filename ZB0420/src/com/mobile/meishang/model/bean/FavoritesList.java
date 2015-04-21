package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class FavoritesList extends Head {
	private String page;
	private String pageCount;
	private List<Goods> mList;

	public FavoritesList() {
	}

	public FavoritesList(JSONObject json) throws JSONException {
		super(json);
		if (json.has("info")) {
			JSONObject jsonObject = json.getJSONObject("info");
			if (jsonObject.has("list")) {
				page = FunctionUtil.getJsonStrValue(jsonObject, "page");
				pageCount = FunctionUtil.getJsonStrValue(jsonObject, "page_count");
				mList = new ArrayList<Goods>();
				JSONArray jsonArray = jsonObject.getJSONArray("list");
				int length = jsonArray.length();
				for (int i = 0; i < length; i++) {
					mList.add(new Goods(jsonArray.getJSONObject(i)));
				}
			}
		}

	}

	public String getPage() {
		return page;
	}

	public String getPageCount() {
		return pageCount;
	}

	public List<Goods> getmList() {
		return mList;
	}

}
