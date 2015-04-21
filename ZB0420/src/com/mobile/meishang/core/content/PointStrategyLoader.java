package com.mobile.meishang.core.content;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.bean.PointStrategyGroup;

public class PointStrategyLoader extends
		HttpRequestLoader<List<PointStrategyGroup>> {

	public PointStrategyLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public List<PointStrategyGroup> handle(String content)
			throws ZLNetworkException {
		List<PointStrategyGroup> mList = new ArrayList<PointStrategyGroup>();
		try {
			JSONArray jsonArray = new JSONArray(content);
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				mList.add(new PointStrategyGroup(jsonArray.getJSONObject(i)));
			}
			return mList;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
