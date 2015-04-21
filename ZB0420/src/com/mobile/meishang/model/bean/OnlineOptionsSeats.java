package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OnlineOptionsSeats extends Head {
	// {
	// "mpid": 571,
	// "movieid": 107,
	// "cinemaid": 476,
	// "cinemaname": "cmts电影城",
	// "moviename": "微爱之渐入佳境",
	// "language": "国语",
	// "edition": "2D",
	// "roomid": 6438,
	// "roomname": "红厅",
	// "playtime": "2015-1-27 22:50:00",
	// "updatetime": "2015-1-27 10:13:14",
	// "price": 40,
	// "bcprice": 42,
	// "lockminute": 30,
	// "maxseat": 60,
	// "servicefee": 2,
	// "seatList": [],
	// "lockedseat": "3:5"
	// "columnSize": 10,
	// "rowSize": 6
	// }
	private String cinemaname;
	private String moviename;
	private String language;
	private String edition;
	private String roomname;
	private String playtime;
	private String maxseat;
	private int columnSize;
	private int rowSize;
	private List<OnlineOptionsSeatsItemRow> mSeatList;

	public OnlineOptionsSeats() {
	}

	public OnlineOptionsSeats(JSONObject json) throws JSONException {
		super(json);
		JSONObject jsonObject = getJsonObject(json, "body");
		cinemaname = getJsonStrValue(jsonObject, "cinemaname");
		moviename = getJsonStrValue(jsonObject, "moviename");
		language = getJsonStrValue(jsonObject, "language");
		edition = getJsonStrValue(jsonObject, "edition");
		roomname = getJsonStrValue(jsonObject, "roomname");
		playtime = getJsonStrValue(jsonObject, "playtime");
		maxseat = getJsonStrValue(jsonObject, "maxseat");
		columnSize = getJsonIntValue(jsonObject, "columnSize");
		rowSize = getJsonIntValue(jsonObject, "rowSize");
		mSeatList = new ArrayList<OnlineOptionsSeatsItemRow>();
		JSONArray jsonArray = jsonObject.getJSONArray("seatList");
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			mSeatList.add(new OnlineOptionsSeatsItemRow(jsonArray
					.getJSONObject(i)));
		}
	}

	public String getCinemaname() {
		return cinemaname;
	}

	public String getMoviename() {
		return moviename;
	}

	public String getLanguage() {
		return language;
	}

	public String getEdition() {
		return edition;
	}

	public String getRoomname() {
		return roomname;
	}

	public String getPlaytime() {
		return playtime;
	}

	public String getMaxseat() {
		return maxseat;
	}

	public List<OnlineOptionsSeatsItemRow> getmSeatList() {
		return mSeatList;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public int getRowSize() {
		return rowSize;
	}

}
