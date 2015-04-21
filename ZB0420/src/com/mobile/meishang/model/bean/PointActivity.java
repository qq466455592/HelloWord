package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.utils.FunctionUtil;

public class PointActivity {

	private String id;
	private String title;
	private String imgUrl;

	public PointActivity() {

	}

	public PointActivity(JSONObject json) throws JSONException {
		id = FunctionUtil.getJsonStrValue(json, "id");
		title = FunctionUtil.getJsonStrValue(json, "title");
		imgUrl = MApplication.getInstance().getmConfig().urlImage
				+ FunctionUtil.getJsonStrValue(json, "imageurl");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
