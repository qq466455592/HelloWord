package com.mobile.meishang.model.bean;

import org.json.JSONObject;

public class Area {
	private String areaName;
	private String areaCode;

	public Area() {
	}

	public Area(String areaName) {
		this.areaName = areaName;
	}

	public Area(JSONObject jsonObject) {
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
