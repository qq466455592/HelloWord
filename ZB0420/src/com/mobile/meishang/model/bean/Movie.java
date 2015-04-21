package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.utils.FunctionUtil;
/**
 * 影片
 * @author Administrator
 *
 */
public class Movie {

	private String id;
	private String name;
	private String description;
	private String price;
	private String score;
	private String type;
	private String district;// 地区:大陆、香港。。
	private String duration;
	private String releaseDate;
	private String imgageUrl;
	private String cinimaQuantity;//影院数
	private String screeningQuantity;//放映场次数

	public Movie() {
	}

	public Movie(JSONObject json) throws JSONException {
		id = FunctionUtil.getJsonStrValue(json, "goods_id");
		name = FunctionUtil.getJsonStrValue(json, "goods_name");
		description = FunctionUtil.getJsonStrValue(json, "goods_brief");
		price = FunctionUtil.getJsonStrValue(json, "shop_price");
		score = FunctionUtil.getJsonStrValue(json, "shop_price");
		type = FunctionUtil.getJsonStrValue(json, "market_price");
		district = FunctionUtil.getJsonStrValue(json, "sales");
		duration = FunctionUtil.getJsonStrValue(json, "is_new");
		releaseDate = FunctionUtil.getJsonStrValue(json, "goods_img");
		imgageUrl = MApplication.getInstance().getmConfig().urlRoot
				+ FunctionUtil.getJsonStrValue(json, "goods_thumb");
		cinimaQuantity = FunctionUtil.getJsonStrValue(json, "goods_img");
		screeningQuantity = FunctionUtil.getJsonStrValue(json, "goods_img");
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

	public String getPrice() {
		return price;
	}

	public String getScore() {
		return score;
	}

	public String getType() {
		return type;
	}

	public String getDistrict() {
		return district;
	}

	public String getDuration() {
		return duration;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getImgageUrl() {
		return imgageUrl;
	}

	public String getCinimaQuantity() {
		return cinimaQuantity;
	}

	public String getScreeningQuantity() {
		return screeningQuantity;
	}

}
