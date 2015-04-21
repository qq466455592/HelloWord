package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieOrdersTicket extends Head {
	// {
	// "tradeno": "100145",
	// "mpid": 703,
	// "movieid": 161,
	// "moviename": "博物馆奇妙夜3（数字IMAX）",
	// "cinemaid": 476,
	// "cinemaname": "cmts电影城",
	// "mobile": "15900765187",
	// "validtime": "2015-2-2 17:58:34",
	// "amount": 62,
	// "unitprice": 62,
	// "quantity": 1,
	// "addtime": "2015-2-2 17:48:34",
	// "roomname": "红厅",
	// "playtime": "2015-2-6 18:25:00",
	// "seat": "2:4",
	// "status": "new",
	// "ukey": "123654789"
	// }
	private String tradeno;
	private String mpid;
	private String movieid;
	private String moviename;
	private String cinemaid;
	private String cinemaname;
	private String mobile;
	private String amount;
	private String unitprice;
	private String quantity;
	private String roomname;

	public MovieOrdersTicket() {
	}

	public MovieOrdersTicket(JSONObject json) throws JSONException {
		tradeno = getJsonStrValue(json, "tradeno");
		mpid = getJsonStrValue(json, "mpid");
		movieid = getJsonStrValue(json, "movieid");
		moviename = getJsonStrValue(json, "moviename");
		cinemaid = getJsonStrValue(json, "cinemaid");
		cinemaname = getJsonStrValue(json, "cinemaname");
		mobile = getJsonStrValue(json, "mobile");
		amount = getJsonStrValue(json, "amount");
		unitprice = getJsonStrValue(json, "unitprice");
		quantity = getJsonStrValue(json, "quantity");
		roomname = getJsonStrValue(json, "roomname");

	}

	public String getTradeno() {
		return tradeno;
	}

	public String getMpid() {
		return mpid;
	}

	public String getMovieid() {
		return movieid;
	}

	public String getMoviename() {
		return moviename;
	}

	public String getCinemaid() {
		return cinemaid;
	}

	public String getCinemaname() {
		return cinemaname;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAmount() {
		return amount;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getRoomname() {
		return roomname;
	}

}
