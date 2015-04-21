package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.utils.FunctionUtil;

public class ChouJiang {
	// {
	// "id": "1",
	// "prize_name": "ceshi 1",
	// "prize_img": "images/201407/1404201234367932991.png",
	// "prize_thumb_img": "images/201407/1404201234579896170.jpg",
	// "start_time": "2014-07-01",
	// "end_time": "2014-07-26",
	// "open_time": "2014-07-31",
	// "lucky_code": "13465789",
	// "state": "1",
	// "updatetime": "1404201234",
	// "content": "测试秒速回第一个行
	// 第二行"
	// }

	private String id;
	private String name;
	private String imageBigUrl;
	private String imageSmallUrl;
	private String startTime;
	private String endTime;
	private String participants;// 参与者
	private String decribe;

	public ChouJiang() {
	}

	public ChouJiang(JSONObject json) throws JSONException {
		id = FunctionUtil.getJsonStrValue(json, "id");
		name = FunctionUtil.getJsonStrValue(json, "prize_name");
		imageBigUrl = MApplication.getInstance().getmConfig().urlImage
				+ FunctionUtil.getJsonStrValue(json, "prize_img");
		imageSmallUrl = MApplication.getInstance().getmConfig().urlImage
				+ FunctionUtil.getJsonStrValue(json, "prize_thumb_img");
		startTime = FunctionUtil.getJsonStrValue(json, "start_time");
		endTime = FunctionUtil.getJsonStrValue(json, "end_time");
		participants = FunctionUtil.getJsonStrValue(json, "num");
		decribe = FunctionUtil.getJsonStrValue(json, "content");
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImageBigUrl() {
		return imageBigUrl;
	}

	public String getImageSmallUrl() {
		return imageSmallUrl;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getParticipants() {
		return participants;
	}

	public String getDecribe() {
		return decribe;
	}

}
