package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.utils.FunctionUtil;

public class ChoujiangItem {
	// [
	// {
	// "id": "1",
	// "prize_thumb_img": "images/201407/1404201234579896170.jpg",
	// "open_time": "2014-07-31",
	// "prize_name": "ceshi 1",
	// "end_time": "2014-07-26",
	// "prizecode": "3457721601",
	// "time": "2014-07-01"
	// },
	// {
	// "id": "11",
	// "prize_thumb_img": "images/201407/1404209136290955298.png",
	// "open_time": "2014-08-15",
	// "prize_name": "ceshi 1",
	// "end_time": "2014-07-31",
	// "prizecode": "3696193576",
	// "time": "2014-07-01"
	// }
	// ]
	private String id;
	private String name;
	private String joinTime;// 抽奖时间
	private String lotteryTime;// 开奖时间
	private String lotteryNumbers;// 抽奖号码
	private String imgageUrl;

	public ChoujiangItem() {
	}

	public ChoujiangItem(JSONObject json) throws JSONException {
		id = FunctionUtil.getJsonStrValue(json, "id");
		name = FunctionUtil.getJsonStrValue(json, "prize_name");
		joinTime = FunctionUtil.getJsonStrValue(json, "time");
		lotteryTime = FunctionUtil.getJsonStrValue(json, "open_time");
		lotteryNumbers = FunctionUtil.getJsonStrValue(json, "prizecode");
		imgageUrl = MApplication.getInstance().getmConfig().urlImage
				+ FunctionUtil.getJsonStrValue(json, "prize_thumb_img");
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public String getLotteryTime() {
		return lotteryTime;
	}

	public String getLotteryNumbers() {
		return lotteryNumbers;
	}

	public String getImgageUrl() {
		return imgageUrl;
	}

}
