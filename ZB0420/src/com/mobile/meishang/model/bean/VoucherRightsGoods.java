package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class VoucherRightsGoods extends Head {
	// {
	// "id": 1,
	// "actid": 1,
	// "goodsid": 1,
	// "code": "0000001",
	// "name": "爱西西里巧克力冰激凌1",
	// "lable": null,
	// "logo": "/staffPhoto/goods/1.png",
	// "status": 1,
	// "detailUrl": "/jsp/act/bus_goods1.jsp",
	// "isDeleted": 1,
	// "createdBy": null,
	// "createdTime": null,
	// "updatedBy": null,
	// "updatedTime": null,
	// "reamrk1": null,
	// "remark2": null,
	// "remark3": null,
	// "desp": "仅需一分钱即可获得爱西西里冰激凌单球一个",
	// "price": 35,
	// "price1": 0.01,
	// "unit": null
	// }

	private String goodsid;
	private String actid;
	private String code;
	private String logo;
	private String name;
	private String desp;
	private String price;
	private String price1;
	private String detailUrl;

	public VoucherRightsGoods() {
	}

	public VoucherRightsGoods(JSONObject json) throws JSONException {
		goodsid = getJsonStrValue(json, "goodsid");
		actid = getJsonStrValue(json, "actid");
		code = getJsonStrValue(json, "code");
		logo = getJsonStrValue(json, "logo");
		name = getJsonStrValue(json, "name");
		desp = getJsonStrValue(json, "desp");
		price = getJsonStrValue(json, "price");
		price1 = getJsonStrValue(json, "price1");
		detailUrl = getJsonStrValue(json, "detailUrl");
	}

	public String getGoodsid() {
		return goodsid;
	}

	public String getActid() {
		return actid;
	}

	public String getCode() {
		return code;
	}

	public String getLogo() {
		return logo;
	}

	public String getName() {
		return name;
	}

	public String getDesp() {
		return desp;
	}

	public String getPrice() {
		return price;
	}

	public String getPrice1() {
		return price1;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

}
