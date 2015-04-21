package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class TravelNotesContent extends Head {
	// {
	// "body": {
	// "title": null,
	// "contents": [],
	// "date": null
	// },
	// "head": {
	// "status": "00",
	// "msg": "操作成功"
	// }
	// }

	private String content;
	private String photo;
	private String contentid;
	private boolean isEdit = true;

	public TravelNotesContent() {

	}

	public TravelNotesContent(JSONObject json) throws JSONException {
		content = getJsonStrValue(json, "content");
		photo = getJsonStrValue(json, "photo");
		contentid = getJsonStrValue(json, "contentid");
		isEdit = false;
	}

	public String getContent() {
		return content;
	}

	public String getPhoto() {
		return photo;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

}
