package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TNotesItemList extends Head {
	// {"body":{
	// travelid //游记id
	// title //标题
	// content //第一条内容
	// createTime //创建时间
	// popularity //人气
	// praise //点赞数
	// comment //评论数
	// },"head":{"status":"00","msg":"操作成功"}}

	private List<TNotesItem> list;

	public TNotesItemList(JSONObject json) throws JSONException {
		super(json);
		JSONObject jsonObject = getJsonObject(json, "body");
		JSONArray jsonArray = getJsonArray(jsonObject, "travel");
		if (jsonArray!=null) {
			list = new ArrayList<TNotesItem>();
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				list.add(new TNotesItem(jsonArray.getJSONObject(i)));
			}
		}

	}

	public List<TNotesItem> getList() {
		return list;
	}

}
