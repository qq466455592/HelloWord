package com.mobile.meishang.model.bean;

public class Message {
	private String title;
	private String content;
	private String date;

	public Message() {

	}

	public Message(String t) {
		title = t;
	}

	public Message(String t, String c, String d) {
		title = t;
		content = c;
		date = d;
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

}
