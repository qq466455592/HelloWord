package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class OnlineOptionsSeatsItem extends Head {
	// {
	// "rownum": "1",
	// "status": "true",
	// "columns": "1"
	// }
	private String rownum;
	private int status;
	private String columns;

	public OnlineOptionsSeatsItem() {
	}

	public OnlineOptionsSeatsItem(JSONObject json) throws JSONException {
		rownum = getJsonStrValue(json, "rownum");
		status = getJsonIntValue(json, "status");
		columns = getJsonStrValue(json, "columns");
	}

	public String getRownum() {
		return rownum;
	}

	/**
	 * 
	 * TODO“0”:已售 “1”:未售 “2”:情侣座 “3”:走廊
	 * 
	 * @throw
	 * @return int
	 */
	public int getStatus() {
		return status;
	}

	public String getColumns() {
		return columns;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
