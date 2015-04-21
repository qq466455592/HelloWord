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
public class AdvertisingItem {
	// Id //商品id
	// Code //商品编号
	// Logo//商品logo
	// Name//商品名
	// Detail//商品详情html地址
	// startTime//活动开始时间
	// endTime//活动结束时间
	// timeToStart//距离活动开始的时间
	// timeToEnd//距离活动结束的时间
	// data/afficheimg/1400545711525617049.jpg"
	private String activeid;
	private String title;
	private String content;
	private String currentPrice;
	private String oldPrice;
	private String sales;
	private String isNew;
	private String imgageUrlBig;
	private String imgageUrlsmall;

	public AdvertisingItem() {
	}

	public AdvertisingItem(int i) {
	}

	public AdvertisingItem(JSONObject json) throws JSONException {
		activeid = FunctionUtil.getJsonStrValue(json, "goods_id");
		title = FunctionUtil.getJsonStrValue(json, "goods_name");
		content = FunctionUtil.getJsonStrValue(json, "content");
		currentPrice = FunctionUtil.getJsonStrValue(json, "promote_price");
		oldPrice = FunctionUtil.getJsonStrValue(json, "market_price");
		sales = FunctionUtil.getJsonStrValue(json, "sales");
		isNew = FunctionUtil.getJsonStrValue(json, "is_new");
		imgageUrlBig = FunctionUtil.getJsonStrValue(json, "goods_img");
		imgageUrlsmall = FunctionUtil.getJsonStrValue(json, "goods_thumb");
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
