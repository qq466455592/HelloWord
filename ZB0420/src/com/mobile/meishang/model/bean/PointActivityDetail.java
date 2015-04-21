package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.utils.FunctionUtil;

public class PointActivityDetail {

	private String id;
	private String imgUrl;
	private String title;
	private String startTime;
	private String endTime;
	private String rules;
	private String products;

	public PointActivityDetail() {

	}

	public PointActivityDetail(JSONObject json) throws JSONException {
		id = FunctionUtil.getJsonStrValue(json, "id");
		imgUrl = MApplication.getInstance().getmConfig().urlImage
				+ FunctionUtil.getJsonStrValue(json, "imageurl");
		title = FunctionUtil.getJsonStrValue(json, "title");
		startTime = FunctionUtil.getJsonStrValue(json, "starttime");
		endTime = FunctionUtil.getJsonStrValue(json, "endtime");
		rules = FunctionUtil.getJsonStrValue(json, "content");
		products = FunctionUtil.getJsonStrValue(json, "product");
	}

	public String getId() {
		return id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getRules() {
		return rules;
	}

	public String getProducts() {
		return products;
	}

}
