package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.utils.FunctionUtil;

public class ChouJiangDetail {
	
//	{
//	    "prize_img": "images/201407/1404209136962172976.png", 
//	    "prize_name": "ceshi 1", 
//	    "nickname": "噢噢噢", 
//	    "lucky_code": "3696193576", 
//	    "mobile": "13776636043"
//	}
	
	// private String id;
	private String imageUrl;
	private String goodsName;
	private String isOpen;//是否开奖标识
	private String drawUser;// 抽奖用户
//	private String drawId;// 抽奖id
	private String WinningId;// 中奖id
	private String mobileNumber;// 手机号

	public ChouJiangDetail() {
	}

	public ChouJiangDetail(JSONObject json) throws JSONException {
		// id = FunctionUtil.getJSONValue(json, "prizeid");
		imageUrl = MApplication.getInstance().getmConfig().urlImage
				+ FunctionUtil.getJsonStrValue(json, "prize_img");
		goodsName = FunctionUtil.getJsonStrValue(json, "prize_name");
		isOpen = FunctionUtil.getJsonStrValue(json, "state");
		drawUser = FunctionUtil.getJsonStrValue(json, "nickname");
//		drawId = FunctionUtil.getJSONValue(json, "endtime");
		WinningId = FunctionUtil.getJsonStrValue(json, "lucky_code");
		mobileNumber = FunctionUtil.getJsonStrValue(json, "mobile");
	}

	// public String getId() {
	// return id;
	// }

	public String getImageUrl() {
		return imageUrl;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getDrawUser() {
		return drawUser;
	}

//	public String getDrawId() {
//		return drawId;
//	}

	public String getWinningId() {
		return WinningId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

}
