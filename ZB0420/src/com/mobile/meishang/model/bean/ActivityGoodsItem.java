package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public class ActivityGoodsItem {
	// "activeid": "1",
	// "title": "测试标题",
	// "content": "测试内容",
	// "price": "10",
	// "oldprice": "15",
	// "sales": "100",
	// "isnew": "1",
	// "imgageurl":
	// "http://www.app.com/data/afficheimg/1400545711525617049.jpg",
	// "small": "http://www.app.com/data/afficheimg/1400545711525617049.jpg"
	private String activeid;
	private String title;
	private String content;
	private String currentPrice;
	private String oldPrice;
	private String sales;
	private String isNew;
	private String imgageUrlBig;
	private String imgageUrlsmall;

	public ActivityGoodsItem() {
	}

	public ActivityGoodsItem(int i) {
	}

	public ActivityGoodsItem(JSONObject json) throws JSONException {
		activeid = FunctionUtil.getJsonStrValue(json, "activeid");
		title = FunctionUtil.getJsonStrValue(json, "title");
		content = FunctionUtil.getJsonStrValue(json, "content");
		currentPrice = FunctionUtil.getJsonStrValue(json, "price");
		oldPrice = FunctionUtil.getJsonStrValue(json, "oldprice");
		sales = FunctionUtil.getJsonStrValue(json, "sales");
		isNew = FunctionUtil.getJsonStrValue(json, "isnew");
		imgageUrlBig = FunctionUtil.getJsonStrValue(json, "imgageurl");
		imgageUrlsmall = FunctionUtil.getJsonStrValue(json, "smallimage");
	}

	public String getActiveid() {
		return activeid;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public String getOldPrice() {
		return oldPrice;
	}

	public String getSales() {
		return sales;
	}

	public String getIsNew() {
		return isNew;
	}

	public String getImgageUrlBig() {
		return imgageUrlBig;
	}

	public String getImgageUrlsmall() {
		return imgageUrlsmall;
	}

}
