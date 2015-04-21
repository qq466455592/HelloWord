package com.mobile.meishang.core.loader;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.bean.MovieTicketList;

public class MyMovieTicketLoader extends HttpRequestLoader<MovieTicketList> {

	public MyMovieTicketLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public MovieTicketList handle(String content) throws ZLNetworkException {
		try {
			JSONObject json = new JSONObject(content);
			MovieTicketList object = new MovieTicketList(json);
			return object;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
