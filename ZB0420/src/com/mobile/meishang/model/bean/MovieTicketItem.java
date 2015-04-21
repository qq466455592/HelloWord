package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieTicketItem extends Head {
	// {
	// "id": 1,
	// "entityType": 2,
	// "entityId": 1,
	// "logo": "http://103.242.168.154:9001/BCLifenull",
	// "name": "微爱之渐入佳境",
	// "desp": "微爱之渐入佳境",
	// "bcCode": "100052",
	// "type": null,
	// "buyerCode": null,
	// "buyerType": null,
	// "bankCode": null,
	// "bankSeq": "100052",
	// "userid": 10,
	// "mobile": "15921366547",
	// "startTime": null,
	// "price3": null,
	// "price2": null,
	// "price1": null,
	// "payTime": null,
	// "allotedTime": null,
	// "payStatus": 1,
	// "goodsNum": null,
	// "lable": null,
	// "status": 1,
	// "createdTime": null,
	// "updatedTime": null,
	// "isDeleted": 0,
	// "reamrk1": null,
	// "remark2": null,
	// "remark3": null
	// }
	private String entityId;
	private String entityType;
	private String logo;
	private String name;
	private String price1;
	private int goodsNum;
	private String status;

	public MovieTicketItem(JSONObject json) throws JSONException {
		entityId = getJsonStrValue(json, "entityId");
		entityType = getJsonStrValue(json, "entityType");
		logo = getJsonStrValue(json, "logo");
		name = getJsonStrValue(json, "name");
		price1 = getJsonStrValue(json, "price1");
		goodsNum = getJsonIntValue(json, "goodsNum");
		status = getJsonStrValue(json, "status");
	}

	public String getEntityId() {
		return entityId;
	}

	public String getEntityType() {
		return entityType;
	}

	public String getLogo() {
		return logo;
	}

	public String getName() {
		return name;
	}

	public String getPrice1() {
		return price1;
	}

	public int getGoodsNum() {
		return goodsNum;
	}

	public String getStatus() {
		return status;
	}

}
