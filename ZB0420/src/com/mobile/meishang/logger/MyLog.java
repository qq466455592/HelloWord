package com.mobile.meishang.logger;

import com.mobile.meishang.config.Config;
import com.mobile.meishang.utils.FileUtil;

import android.util.Log;

public class MyLog {
	@SuppressWarnings("unused")
	private static final String TAG = "== LogTrace ==";
	private static final String DEBUG_TAG = "debug";

	private static final boolean DEBUG = Config.DEBUG;

	public static void e(Object object, String err) {
		if (DEBUG) {
			Log.e(getPureClassName(object), err);
		}
	}

	public static void e(String tag, String err) {
		if (DEBUG) {
			Log.e(tag, err);
		}
	}

	public static void e(String msg) {
		if (DEBUG) {
			Log.e(DEBUG_TAG, msg);
		}
	}

	public static void d(Object object, String debug) {
		if (DEBUG) {
			Log.d(getPureClassName(object), debug);
		}
	}

	public static void d(String tag, String debug) {
		if (DEBUG) {
			Log.d(tag, debug);
		}
	}

	public static void d(String tag, String debug, Throwable e) {
		if (DEBUG) {
			Log.d(tag, debug, e);
		}
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public static void d(String msg) {
		if (DEBUG) {
			Log.d(DEBUG_TAG, msg);
		}
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public static void i(Object object, String info) {
		if (DEBUG) {
			Log.i(getPureClassName(object), info);
		}
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public static void i(String msg) {
		if (DEBUG) {
			Log.i(DEBUG_TAG, msg);
		}
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public static void w(Object object, String info) {
		if (DEBUG) {
			Log.w(getPureClassName(object), info);
		}
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public static void w(String msg) {
		if (DEBUG) {
			Log.w(DEBUG_TAG, msg);
		}
	}

	public static void jw(Object object, Throwable tr) {
		if (DEBUG) {
			Log.w(getPureClassName(object), "", filterThrowable(tr));
		}
	}

	public static void je(Object object, Throwable tr) {
		if (DEBUG) {
			Log.e(getPureClassName(object), "", filterThrowable(tr));
		}
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	private static Throwable filterThrowable(Throwable tr) {
		StackTraceElement[] ste = tr.getStackTrace();
		tr.setStackTrace(new StackTraceElement[] { ste[0] });
		return tr;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	private static String getPureClassName(Object object) {
		if (object == null) {
			// Log.e(TAG, "getPureClassName() : object is null.");
		}
		String name = object.getClass().getName();
		if ("java.lang.String".equals(name)) {
			return object.toString();
		}
		int idx = name.lastIndexOf('.');
		if (idx > 0) {
			return name.substring(idx + 1);
		}
		return name;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public static void logToFile(String msg, String path) {
		if (Config.DEBUG) {
			FileUtil.writeStringToFile(msg, path);
		}
	}
}
