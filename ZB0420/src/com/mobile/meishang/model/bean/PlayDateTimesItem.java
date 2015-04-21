package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class PlayDateTimesItem extends Head implements Parcelable {
	// {
	// "mpid": 499,
	// "movieid": 121,
	// "cinemaid": 476,
	// "cinemaname": "cmts电影城",
	// "moviename": "怨.灵人偶（数字3D）",
	// "language": "国语4D",
	// "edition": "3D",
	// "roomid": 6438,
	// "roomname": "红厅",
	// "playtime": "2015-1-27 12:45:00",
	// "updatetime": "2015-1-23 9:50:25",
	// "price": 50,
	// "bcprice": 52,
	// "lockminute": 30,
	// "maxseat": 60,
	// "servicefee": 2
	// }

	private String mpid;
	private String movieid;
	private String cinemaid;
	private String playtime;
	private String edition;
	private String roomname;
	private String price;
	private String bcprice;

	public PlayDateTimesItem() {
	}

	public PlayDateTimesItem(Parcel in) {

		mpid = in.readString();
		movieid = in.readString();
		cinemaid = in.readString();
		edition = in.readString();
		roomname = in.readString();
		price = in.readString();
		bcprice = in.readString();
	}

	public PlayDateTimesItem(JSONObject json) throws JSONException {
		mpid = getJsonStrValue(json, "mpid");
		movieid = getJsonStrValue(json, "movieid");
		cinemaid = getJsonStrValue(json, "cinemaid");
		playtime = getJsonStrValue(json, "playtime");
		edition = getJsonStrValue(json, "edition");
		roomname = getJsonStrValue(json, "roomname");
		price = getJsonStrValue(json, "price");
		bcprice = getJsonStrValue(json, "bcprice");
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(mpid);
		dest.writeString(movieid);
		dest.writeString(cinemaid);
		dest.writeString(playtime);
		dest.writeString(edition);
		dest.writeString(roomname);
		dest.writeString(price);
		dest.writeString(bcprice);

	}

	public static final Parcelable.Creator<PlayDateTimesItem> CREATOR = new Creator<PlayDateTimesItem>() {

		@Override
		public PlayDateTimesItem[] newArray(int size) {
			return new PlayDateTimesItem[size];
		}

		@Override
		public PlayDateTimesItem createFromParcel(Parcel source) {
			return new PlayDateTimesItem(source);
		}
	};

	public String getMpid() {
		return mpid;
	}

	public void setMpid(String mpid) {
		this.mpid = mpid;
	}

	public String getMovieid() {
		return movieid;
	}

	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}

	public String getCinemaid() {
		return cinemaid;
	}

	public void setCinemaid(String cinemaid) {
		this.cinemaid = cinemaid;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBcprice() {
		return bcprice;
	}

	public void setBcprice(String bcprice) {
		this.bcprice = bcprice;
	}

	public String getPlaytime() {
		return playtime;
	}

	public void setPlaytime(String playtime) {
		this.playtime = playtime;
	}

}
