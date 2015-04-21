package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class TNotesItem extends Head {
	// {"body":{
	// travelid //游记id
	// title //标题
	// content //第一条内容
	// createTime //创建时间
	// popularity //人气
	// praise //点赞数
	// comment //评论数
	// },"head":{"status":"00","msg":"操作成功"}}

	private String travelid;
	private String title;
	private String content;
	private String createTime;
	private String popularity;
	private String praise;
	private String comment;

	public TNotesItem(JSONObject json) throws JSONException {
		travelid = getJsonStrValue(json, "travelid");
		title = getJsonStrValue(json, "title");
		content = getJsonStrValue(json, "content");
		createTime = getJsonStrValue(json, "createTime");
		popularity = getJsonStrValue(json, "popularity");
		praise = getJsonStrValue(json, "praise");
		comment = getJsonStrValue(json, "comment");
	}

	public String getTravelid() {
		return travelid;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getPopularity() {
		return popularity;
	}

	public String getPraise() {
		return praise;
	}

	public String getComment() {
		return comment;
	}

}
