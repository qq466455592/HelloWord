package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class City {
	private String cityAlpha;
	private String cityName;
	private String cityCode;

	public City() {
	}

	public City(String alpha, String name) {
		cityAlpha = alpha;
		this.cityName = name;
	}

	public City(JSONObject json) throws JSONException {
		cityAlpha = FunctionUtil.getJsonStrValue(json, "firstletter");
		cityName = FunctionUtil.getJsonStrValue(json, "cityname");
		cityCode = FunctionUtil.getJsonStrValue(json, "cityid");
	}

	public String getCityAlpha() {
		return cityAlpha;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

}
