package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CertificateList extends Head {
	private List<CertificateItem> mList;

	public CertificateList() {
	}

	public CertificateList(JSONObject json) throws JSONException {
		super(json);
		if (json.has("info")) {
			JSONObject jsonObject = json.getJSONObject("info");
			if (jsonObject.has("list")) {
				mList = new ArrayList<CertificateItem>();
				JSONArray jsonArray = jsonObject.getJSONArray("list");
				int length = jsonArray.length();
				for (int i = 0; i < length; i++) {
					mList.add(new CertificateItem(jsonArray.getJSONObject(i)));
				}
			}
		}
	}

	public List<CertificateItem> getmList() {
		return mList;
	}

}
