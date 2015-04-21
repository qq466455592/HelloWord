package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TravelNotes extends Head {
	// {
	// "body": {
	// "title": null,
	// "contents": [],
	// "date": null
	// },
	// "head": {
	// "status": "00",
	// "msg": "操作成功"
	// }
	// }

	private String title;
	private String date;
	private List<TravelNotesContent> tNotesContents;

	public TravelNotes() {

	}

	public TravelNotes(JSONObject jsonObject) throws JSONException {
		super(jsonObject);
		JSONObject json = getJsonObject(jsonObject, "body");
		title = getJsonStrValue(json, "title");
		date = getJsonStrValue(json, "date");
		JSONArray jsonArray = getJsonArray(json, "contents");
		int length = jsonArray.length();
		tNotesContents = new ArrayList<TravelNotesContent>();
		for (int i = 0; i < length; i++) {
			tNotesContents.add(new TravelNotesContent(jsonArray
					.getJSONObject(i)));
		}
	}

	public String getTitle() {
		return title;
	}

	public String getDate() {
		return date;
	}

	public List<TravelNotesContent> gettNotesContents() {
		return tNotesContents;
	}

}
