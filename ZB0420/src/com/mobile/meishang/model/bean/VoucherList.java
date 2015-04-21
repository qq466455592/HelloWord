package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VoucherList extends Head {

	private List<VoucherRights> list;

	public VoucherList(JSONObject json) throws JSONException {
		super(json);
		JSONObject jsonObject = getJsonObject(json, "body");
		JSONArray jsonArray = getJsonArray(jsonObject, "accRights");
		if (jsonArray != null) {
			list = new ArrayList<VoucherRights>();
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				list.add(new VoucherRights(jsonArray.getJSONObject(i)));
			}
		}

	}

	public List<VoucherRights> getList() {
		return list;
	}

}
