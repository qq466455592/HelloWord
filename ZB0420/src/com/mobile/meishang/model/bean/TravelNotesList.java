package com.mobile.meishang.model.bean;

public class TravelNotesList {
	// userid: 用户id
	// title: 游记标题
	// content: 游记内容
	// photo：图片

	private String title;
	private String date;
	private String content;
	private String imgUrl;
	private boolean isEdit = true;

	public TravelNotesList() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
