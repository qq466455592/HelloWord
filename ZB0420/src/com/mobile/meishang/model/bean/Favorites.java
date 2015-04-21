package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class Favorites {
	// "goods_id": "33",
	// "goods_name": "西树泡芙某特价商品",
	// "market_price": "￥24元",
	// "shop_price": "￥20元",
	// "promote_price": "",
	// "goods_brief": "",
	// "goods_thumb": "images/201406/thumb_img/33_thumb_G_1401642234314.jpg",
	// "goods_img": "images/201406/goods_img/33_G_1401642234014.jpg",
	// "url": "goods.php?id=33"

	private String activeid;
	private String title;
	private String content;
	private String currentPrice;
	private String oldPrice;
	private String sales;
	private String isNew;
	private String imgageUrlBig;
	private String imgageUrlsmall;

	public Favorites() {
	}

	public Favorites(JSONObject json) throws JSONException {
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
