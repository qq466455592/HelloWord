package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public class CombineListItem {
	private String id;
	private String title;
	private String imgageUrlBig;
	private String imgageUrlsmall;

	public CombineListItem() {
	}

	public CombineListItem(int i) {
	}

	public CombineListItem(JSONObject json) throws JSONException {
		id = FunctionUtil.getJsonStrValue(json, "activeid");
		title = FunctionUtil.getJsonStrValue(json, "title");
		imgageUrlBig = FunctionUtil.getJsonStrValue(json, "imgageurl");
		imgageUrlsmall = FunctionUtil.getJsonStrValue(json, "smallimage");
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getImgageUrlBig() {
		return imgageUrlBig;
	}

	public String getImgageUrlsmall() {
		return imgageUrlsmall;
	}

}
