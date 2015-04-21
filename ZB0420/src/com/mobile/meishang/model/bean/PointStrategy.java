package com.mobile.meishang.model.bean;

public class PointStrategy {
	private String title;
	private String content;

	public PointStrategy() {

	}

	public PointStrategy(String t) {
		title = t;
	}

	public PointStrategy(String t, String c) {
		title = t;
		content = c;
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


}
