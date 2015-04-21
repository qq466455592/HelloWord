package com.mobile.meishang;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.cookie.Cookie;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.mobile.meishang.config.Config;
import com.mobile.meishang.imagecache.ImageLruCache;
import com.mobile.meishang.logger.MyLog;
import com.mobile.meishang.model.bean.Login;

public class MApplication extends Application {
	public static MApplication mApplication;
	private static ImageLruCache mImageLruCache;
	private static int mLongest;
	private SharedPreferences mPrefs;
	private Config mConfig;
	private int mCurrentDensity;
	private int mCurrentWidthPixels;
	private int mCurrentHeightPixels;
	private String signValue;
	private List<Cookie> listCookies;
	private Login login;

	@Override
	public void onCreate() {
		super.onCreate();
		// SuningCrashExceptionHandler.getInstance().init(getApplicationContext());
		mApplication = this;
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		mConfig = new Config(mPrefs);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((WindowManager) getApplicationContext().getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
				displayMetrics);
		mCurrentDensity = (int) displayMetrics.density;
		mCurrentWidthPixels = displayMetrics.widthPixels;
		mCurrentHeightPixels = displayMetrics.heightPixels;
		MyLog.i("屏幕分辨率：" + displayMetrics.widthPixels + "*"
				+ displayMetrics.heightPixels + " density "
				+ displayMetrics.density + " " + displayMetrics.densityDpi);

		// 每个星期清空一下磁盘
		if (mConfig.getLastUpdateTime() == 0) {
			mImageLruCache = ImageLruCache.findOrCreateCache(this, "images",
					false);
			mConfig.setLastUpdateTime(System.currentTimeMillis());
		} else {
			final long diff = System.currentTimeMillis()
					- mConfig.getLastUpdateTime();
			final long valid = 7 * 24 * 60 * 60 * 1000; // one week in
			if (diff < valid)
				mImageLruCache = ImageLruCache.findOrCreateCache(this,
						"images", false);
			else {
				mImageLruCache = ImageLruCache.findOrCreateCache(this,
						"images", true);
				mConfig.setLastUpdateTime(System.currentTimeMillis());
			}
		}
		listCookies = new ArrayList<Cookie>();
	}

	@Override
	public void onLowMemory() {
		if (mImageLruCache != null) {
			mImageLruCache.clearCaches();
		}
		super.onLowMemory();
	}

	public static ImageLruCache getImageLruCache() {
		return mImageLruCache;
	}

	public static void setImageLruCache(ImageLruCache imageLruCache) {
		mImageLruCache = imageLruCache;
	}

	public static void setLongest(int l) {
		mLongest = l;
	}

	public static int getLongest() {
		return mLongest;
	}

	public static MApplication getInstance() {
		return mApplication;
	}

	public Config getmConfig() {
		return mConfig;
	}

	// public static Timer getTimer() {
	// return mTimer;
	// }

	// public void setCurrentDensity(int currentDensity) {
	// mCurrentDensity = currentDensity;
	// }
	//
	// public int getCurrentDensity() {
	// return mCurrentDensity;
	// }
	//
	// public void setCurrentWidthPixels(int currentWidthPixels) {
	// mCurrentWidthPixels = currentWidthPixels;
	// }
	//
	// public int getCurrentWidthPixels() {
	// return mCurrentWidthPixels;
	// }
	//
	// public int getmCurrentHeightPixels() {
	// return mCurrentHeightPixels;
	// }
	//
	// public void setmCurrentHeightPixels(int mCurrentHeightPixels) {
	// this.mCurrentHeightPixels = mCurrentHeightPixels;
	// }
	public List<Cookie> getListCookies() {
		return listCookies;
	}

	public void setListCookies(List<Cookie> listCookies) {
		this.listCookies = listCookies;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public boolean checkLogin() {
		if (login != null) {
			return true;
		}
		return false;

	}

}