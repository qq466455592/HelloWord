package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VoucherRights extends Head {
	// {
	// "busGoods": null,
	// "id": 3,
	// "code": "0000003",
	// "type": "1",
	// "buyerCode": "bclife",
	// "buyerType": "3",
	// "startTime": 1420099200000,
	// "endTime": 1451548800000,
	// "expiredTime": null,
	// "expiredInterval": null,
	// "bcBalanceInit": 35,
	// "bcBalance": 35,
	// "limitType": "2",
	// "archiveType": "1",
	// "userid": 10,
	// "actids": ",1,2,3,",
	// "goodsids": ",1,2,3,",
	// "status": 3,
	// "createdTime": null,
	// "updatedTime": null,
	// "desp": null,
	// "isDeleted": 0,
	// "reamrk1": null,
	// "remark2": null,
	// "remark3": null,
	// "bankCode": "alipay",
	// "bankSeq": "010917393052030",
	// "generatedTime": 1420853970511,
	// "allotedTime": 1420855770511,
	// "payStatus": 1,
	// "busGoodss": [
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
	// ]
	// }

	private String rcode;
	private int status;
	private long endTime;
	private List<VoucherRightsGoods> mList;

	public VoucherRights() {
	}

	public VoucherRights(JSONObject json) throws JSONException {
		rcode = getJsonStrValue(json, "code");
		status = getJsonIntValue(json, "status");
		endTime = getJsonLongtValue(json, "endTime");
		JSONArray jsonArray = getJsonArray(json, "busGoodss");
		int length = jsonArray.length();
		mList = new ArrayList<VoucherRightsGoods>();
		for (int i = 0; i < length; i++) {
			mList.add(new VoucherRightsGoods(jsonArray.getJSONObject(i)));
		}
	}

	public String getRcode() {
		return rcode;
	}

	public long getEndTime() {
		return endTime;
	}

	public int getStatus() {
		return status;
	}

	public List<VoucherRightsGoods> getmList() {
		return mList;
	}

}
