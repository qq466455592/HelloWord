package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieOrders extends Head {
	// {
	// "bankSeq": "020217495793794",
	// "callBack_Url": "http://103.242.168.154:9001/BCLife/lifeMovie/pay",
	// "ticketOrder": {
	// }
	// }
	private String bankSeq;
	private String callBackUrl;
	private MovieOrdersTicket ticket;

	public MovieOrders() {
	}

	public MovieOrders(JSONObject json) throws JSONException {
		super(json);
		JSONObject bodyObject = getJsonObject(json, "body");
		bankSeq = getJsonStrValue(bodyObject, "bankSeq");
		callBackUrl = getJsonStrValue(bodyObject, "callBackUrl");
		ticket = new MovieOrdersTicket(getJsonObject(bodyObject, "ticketOrder"));

	}

	public String getBankSeq() {
		return bankSeq;
	}

	public String getcallBackUrl() {
		return callBackUrl;
	}

	public MovieOrdersTicket getTicket() {
		return ticket;
	}

}
