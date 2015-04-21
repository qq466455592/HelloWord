package com.mobile.meishang.ui.home.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.config.Constants;
import com.mobile.meishang.logger.MyLog;

public class LocationNetwork implements AMapLocationListener {
	private LocationManagerProxy aMapLocManager = null;

	public LocationNetwork() {
	}

	public LocationNetwork(Context context) {
		aMapLocManager = LocationManagerProxy.getInstance(context);
		/*
		 * mAMapLocManager.setGpsEnable(false);//
		 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
		 * API定位采用GPS和网络混合定位方式
		 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
		 */
		aMapLocManager.requestLocationUpdates(
				LocationProviderProxy.AMapNetwork, 2000, 10, this);
	}

	@Override
	public void onLocationChanged(Location location) {

	}

	@Override
	public void onStatusChanged(String s, int i, Bundle bundle) {

	}

	@Override
	public void onProviderEnabled(String s) {

	}

	@Override
	public void onProviderDisabled(String s) {

	}

	@Override
	public void onLocationChanged(AMapLocation location) {
		if (location != null) {
			// Double geoLat = location.getLatitude();
			// Double geoLng = location.getLongitude();
			// String cityCode = "";
			// String desc = "";
			// Bundle locBundle = location.getExtras();
			// if (locBundle != null) {
			// cityCode = locBundle.getString("citycode");
			// desc = locBundle.getString("desc");
			// }
			// String str = ("定位成功:(" + geoLng + "," + geoLat + ")"
			// + "\n精    度    :" + location.getAccuracy() + "米"
			// + "\n定位方式:" + location.getProvider() + "\n定位时间:"
			// + FunctionUtil.formatTime(location.getTime()) + "\n城市编码:"
			// + cityCode + "\n位置描述:" + desc + "\n省:"
			// + location.getProvince() + "\n市:" + location.getCity()
			// + "\n区(县):" + location.getDistrict() + "\n区域编码:" + location
			// .getAdCode());
			MyLog.d(location.getCity());

			MApplication.getInstance().getmConfig()
					.putPreferencesVal(Constants.CITY_NAME, location.getCity());
			MApplication
					.getInstance()
					.getmConfig()
					.putPreferencesVal(Constants.LONGITUDE,
							location.getLongitude() + "");
			MApplication
					.getInstance()
					.getmConfig()
					.putPreferencesVal(Constants.LATITUDE,
							location.getLatitude() + "");
			stopLocation();
		}

	}

	/**
	 * 销毁定位
	 */
	private void stopLocation() {
		if (aMapLocManager != null) {
			aMapLocManager.removeUpdates(this);
			aMapLocManager.destory();
		}
		aMapLocManager = null;
	}
}
