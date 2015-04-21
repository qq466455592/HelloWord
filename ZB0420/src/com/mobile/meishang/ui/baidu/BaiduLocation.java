/* 
 * @Title:  LocationActivity.java 
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved 
 * @Description:  TODO<请描述此文件是做什么的> 
 * @author:  ZhangBin
 * @data:  2015-1-16 上午10:37:15 
 * @version:  V1.0 
 */
package com.mobile.meishang.ui.baidu;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.config.Constants;
import com.mobile.meishang.logger.MyLog;

public class BaiduLocation {
	private LocationClient mLocationClient;
	private BDLocationListener myListener = new MyLocationListener();

	public BaiduLocation(Context context) {
		mLocationClient = new LocationClient(context); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		InitLocation();
	}

	private void InitLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		// option.setLocationMode( LocationMode.Battery_Saving);//设置定位模式
		// option.setLocationMode( LocationMode.Device_Sensors);//设置定位模式
		// 返回国测局经纬度坐标系 coor=gcj02
		// 返回百度墨卡托坐标系 coor=bd09
		// 返回百度经纬度坐标系 coor=bd09ll
		String tempcoor = "bd09ll";// gcj02,bd09ll,bd09
		option.setCoorType(tempcoor);// 返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		// option.setIsNeedAddress(true);// ;是否反地理编码
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		// if (mLocationClient != null && mLocationClient.isStarted()) {
		// mLocationClient.requestLocation();
		// }
	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\ndirection : ");
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append(location.getDirection());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				// 运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
			}
			MyLog.d("location", sb.toString());
			if (location != null) {
				mLocationClient.stop();
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

			}
		}

	}
}
