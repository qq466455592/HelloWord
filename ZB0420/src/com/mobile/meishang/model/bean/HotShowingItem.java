package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import com.mobile.meishang.MApplication;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public class HotShowingItem extends Head implements Parcelable {
	// " movieid ":影片id
	// " moviename ":影片名称
	// " englishname ":影片英文名称
	// " language ":语言
	// " type ":影片类型
	// " state ":出产地区
	// " director ":导演
	// " actors ":主演
	// " length ":片长
	// " highlight ":一句话影评
	// " releasedate ":电影的首映日期
	// " logo ":影片logo
	// " content ":电影详情介绍
	// " imdbid ": imdbid
	// " minprice ":最低票价
	// " collectedtimes ":关注数
	// " clickedtimes ":感兴趣数
	// " generalmark ":影片评分
	// " gcedition ":影片版本
	// " generalmark ":购票数

	private String movieid;
	private String moviename;
	private String englishname;
	private String language;
	private String type;
	private String state;
	private String director;
	private String actors;
	private String length;
	private String imgBigUrl;
	private String imgSmallUrl;

	public HotShowingItem() {
	}

	public HotShowingItem(Parcel in) {

		movieid = in.readString();
		moviename = in.readString();
		englishname = in.readString();
		language = in.readString();
		type = in.readString();
		state = in.readString();
		director = in.readString();
		actors = in.readString();
		length = in.readString();
		imgBigUrl = in.readString();
		imgSmallUrl = in.readString();
	}

	public HotShowingItem(JSONObject json) throws JSONException {
		movieid = getJsonStrValue(json, "movieid");
		moviename = getJsonStrValue(json, "moviename");
		englishname = getJsonStrValue(json, "englishname");
		language = getJsonStrValue(json, "language");
		type = getJsonStrValue(json, "type");
		state = getJsonStrValue(json, "state");
		director = getJsonStrValue(json, "director");
		actors = getJsonStrValue(json, "actors");
		length = getJsonStrValue(json, "length");
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/staffPhoto/movieLogo/");
		urlString.append(movieid);
		String url = urlString.toString();
		imgBigUrl = url + "_big1.jpg";
		imgSmallUrl = url + "_small1.jpg";
	}

	public String getMovieid() {
		return movieid;
	}

	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getEnglishname() {
		return englishname;
	}

	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getImgBigUrl() {
		return imgBigUrl;
	}

	public void setImgBigUrl(String imgBigUrl) {
		this.imgBigUrl = imgBigUrl;
	}

	public String getImgSmallUrl() {
		return imgSmallUrl;
	}

	public void setImgSmallUrl(String imgSmallUrl) {
		this.imgSmallUrl = imgSmallUrl;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(movieid);
		dest.writeString(moviename);
		dest.writeString(englishname);
		dest.writeString(language);
		dest.writeString(type);
		dest.writeString(state);
		dest.writeString(director);
		dest.writeString(actors);
		dest.writeString(length);
		dest.writeString(imgBigUrl);
		dest.writeString(imgSmallUrl);

	}

	public static final Parcelable.Creator<HotShowingItem> CREATOR = new Creator<HotShowingItem>() {

		@Override
		public HotShowingItem[] newArray(int size) {
			return new HotShowingItem[size];
		}

		@Override
		public HotShowingItem createFromParcel(Parcel source) {
			return new HotShowingItem(source);
		}
	};

}
