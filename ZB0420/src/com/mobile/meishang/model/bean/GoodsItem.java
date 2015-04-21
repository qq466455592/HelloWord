package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class GoodsItem extends Head {
	// "goods_id": "33",
	// "goods_name": "西树泡芙某特价商品",
	// "market_price": "￥24元",
	// "shop_price": "￥20元",
	// "promote_price": "",
	// "goods_brief": "",
	// "goods_thumb": "images/201406/thumb_img/33_thumb_G_1401642234314.jpg",
	// "goods_img": "images/201406/goods_img/33_G_1401642234014.jpg",
	// "url": "goods.php?id=33"

	private String id;
	private String title;
	private String content;
	private String currentPrice;
	private String oldPrice;
	private String sales;
	private String isNew;// 1是新品。0 不是
	private String imgageUrlBig;
	private String imgageUrlsmall;

	public GoodsItem() {
	}

	public GoodsItem(JSONObject json) throws JSONException {
		id = getJsonStrValue(json, "goods_id");
		title = getJsonStrValue(json, "goods_name");
		content = getJsonStrValue(json, "goods_brief");
		currentPrice = getJsonStrValue(json, "promote_price");
		oldPrice = getJsonStrValue(json, "market_price");
		sales = getJsonStrValue(json, "sales");
		isNew = getJsonStrValue(json, "is_new");

	}

	public String getId() {
		return id;
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
