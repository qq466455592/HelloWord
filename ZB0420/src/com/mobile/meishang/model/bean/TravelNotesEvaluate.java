package com.mobile.meishang.model.bean;

public class TravelNotesEvaluate {
	private String name = "美味优店";
	private String date;
	private String content;
	private String score;

	public TravelNotesEvaluate() {

	}

	public TravelNotesEvaluate(String n) {
		name += n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
