package com.mobile.meishang.core.content;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.bean.PointActivity;

public class PointActivityListLoader extends
		HttpRequestLoader<List<PointActivity>> {

	public PointActivityListLoader(Context context,
			ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public List<PointActivity> handle(String content) throws ZLNetworkException {
		List<PointActivity> pointActivities = new ArrayList<PointActivity>();
		try {
			JSONArray jsonArray = new JSONArray(content);
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				pointActivities.add(new PointActivity(jsonArray
						.getJSONObject(i)));
			}
			return pointActivities;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
