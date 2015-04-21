package com.mobile.meishang.model.bean;

public class CategoryFilter {
	private String name;
	private String id;
	private boolean isChecked;

	public CategoryFilter() {

	}

	public CategoryFilter(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

}
