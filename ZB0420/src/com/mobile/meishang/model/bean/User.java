package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class User {

	private String nickname;
	private String sex;
	private String address;
	private String email;
	private String birthday;

	public User() {
	}

	public User(JSONObject json) throws JSONException {
		nickname = FunctionUtil.getJsonStrValue(json, "nickname");
		sex = FunctionUtil.getJsonStrValue(json, "sex");
		address = FunctionUtil.getJsonStrValue(json, "address");
		email = FunctionUtil.getJsonStrValue(json, "email");
		birthday = FunctionUtil.getJsonStrValue(json, "birthday");

	}

	public String getNickname() {
		return nickname;
	}

	public String getSex() {
		return sex;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getBirthday() {
		return birthday;
	}

}
