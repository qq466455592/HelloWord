package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class Login extends Head {
	// {"head":{"status":"00","msg":"操作成功"},
	// "body":{[
	// account: //用户名
	// headIcon: //头像
	// userAttention : //用户关注
	// userFans： //用户粉丝
	// nickName: //昵称
	// desp : //简介
	// sex : //性别
	// ] }
	// }
	private String account;
	private String headIcon;
	private String userAttention;
	private String userFans;
	private String nickName;
	private String desp;
	private String sex;

	public Login() {

	}

	public Login(JSONObject jsonObject) throws JSONException {
		super(jsonObject);
		JSONObject jsonBody = FunctionUtil.getJsonObject(jsonObject, "body");
		JSONObject json = FunctionUtil.getJsonObject(jsonBody, "info");
		if (json != null) {
			account = FunctionUtil.getJsonStrValue(json, "account");
			headIcon = FunctionUtil.getJsonStrValue(json, "headIcon");
			userAttention = FunctionUtil.getJsonStrValue(json, "userAttention");
			userFans = FunctionUtil.getJsonStrValue(json, "userFans");
			nickName = FunctionUtil.getJsonStrValue(json, "nickName");
			desp = FunctionUtil.getJsonStrValue(json, "desp");
			sex = FunctionUtil.getJsonStrValue(json, "sex");

		}
	}

	public String getAccount() {
		return account;
	}

	public String getHeadIcon() {
		return headIcon;
	}

	public String getUserAttention() {
		return userAttention;
	}

	public String getUserFans() {
		return userFans;
	}

	public String getNickName() {
		return nickName;
	}

	public String getDesp() {
		return desp;
	}

	public String getSex() {
		return sex;
	}

}
