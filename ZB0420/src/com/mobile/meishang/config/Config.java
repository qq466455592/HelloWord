package com.mobile.meishang.config;

import android.content.SharedPreferences;

public class Config {
	public static final boolean DEBUG = true;
	// public String urlRoot = "www.yudian-life.com";
	// public String urlRootApi = "http://www.yudian-life.com";
	// public String urlImage = "http://www.app.com/";
	public String urlRoot = "http://103.242.168.154:9001/BCLife";
	public String urlRootApi = "http://103.242.168.154:9001/BCLife";
	// public String urlRootApi = "http://192.168.0.170:8080/BCLife";
	public String urlImage = "http://103.242.168.154:9001/BCLife";
	public SharedPreferences mPrefs;

	public Config(SharedPreferences mPrefs) {
		this.mPrefs = mPrefs;
	}

	public int getGuideFlag() {
		return mPrefs.getInt(Constants.GUIDEFLAG, 0);
	}

	public boolean setGuideFlag(int flag) {
		return mPrefs.edit().putInt(Constants.GUIDEFLAG, flag).commit();
	}

	public long getLastUpdateTime() {
		return mPrefs.getLong(Constants.Last_PICTURE_Update_Time, 0);
	}

	public boolean setLastUpdateTime(long updateTime) {
		return mPrefs.edit()
				.putLong(Constants.Last_PICTURE_Update_Time, updateTime)
				.commit();
	}

	public void putPreferencesVal(String key, int value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public long getPreferencesVal(String key, int defaultVal) {
		return mPrefs.getInt(key, defaultVal);
	}

	public void putPreferencesVal(String key, long value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public long getPreferencesVal(String key, long defaultVal) {
		return mPrefs.getLong(key, defaultVal);
	}

	public void putPreferencesVal(String key, boolean value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public boolean getPreferencesVal(String key, boolean defaultVal) {
		return mPrefs.getBoolean(key, defaultVal);
	}

	public void putPreferencesVal(String key, String value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getPreferencesVal(String key, String defaultVal) {
		return mPrefs.getString(key, defaultVal);
	}
}
