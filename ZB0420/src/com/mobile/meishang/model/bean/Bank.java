package com.mobile.meishang.model.bean;

public class Bank {
	private String id;
	private String name;

	public Bank() {

	}

	public Bank(String t) {
		name = t;
	}

	public Bank(String t, String d) {
		name = t;
		id = d;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
