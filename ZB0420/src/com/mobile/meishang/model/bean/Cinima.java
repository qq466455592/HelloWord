package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.utils.FunctionUtil;

/**
 * 影院
 * 
 * @author Administrator
 * 
 */
public class Cinima {

	private String id;
	private String name;
	private String description;
	private String floorPrice;
	private String score;
	private String shopName;
	private String shopAddress;
	private String isBooking;
	private String imgageUrl;

	public Cinima() {
	}

	public Cinima(JSONObject json) throws JSONException {
		id = FunctionUtil.getJsonStrValue(json, "goods_id");
		name = FunctionUtil.getJsonStrValue(json, "goods_name");
		description = FunctionUtil.getJsonStrValue(json, "goods_brief");
		floorPrice = FunctionUtil.getJsonStrValue(json, "shop_price");
		score = FunctionUtil.getJsonStrValue(json, "shop_price");
		shopName = FunctionUtil.getJsonStrValue(json, "market_price");
		shopAddress = FunctionUtil.getJsonStrValue(json, "sales");
		isBooking = FunctionUtil.getJsonStrValue(json, "is_new");
		imgageUrl = MApplication.getInstance().getmConfig().urlRoot
				+ FunctionUtil.getJsonStrValue(json, "goods_thumb");
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getFloorPrice() {
		return floorPrice;
	}

	public String getScore() {
		return score;
	}

	public String getShopName() {
		return shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public String getIsBooking() {
		return isBooking;
	}

	public String getImgageUrl() {
		return imgageUrl;
	}

}
