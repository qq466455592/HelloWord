package com.mobile.meishang;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.os.Looper;

import com.mobile.meishang.logger.MyLog;

public class MCrashExceptionHandler implements UncaughtExceptionHandler {
	// private Context mContext;

	/** 系统默认的UncaughtException处理类 */
	// private Thread.UncaughtExceptionHandler mDefaultHandler;
	private static MCrashExceptionHandler mCrashHandler = new MCrashExceptionHandler();

	private MCrashExceptionHandler() {
	}

	public static synchronized MCrashExceptionHandler getInstance() {

		if (mCrashHandler != null) {
			return mCrashHandler;
		} else {
			mCrashHandler = new MCrashExceptionHandler();
			return mCrashHandler;
		}
	}

	public void init(Context context) {
		// this.mContext = context;
		// mDefaultHandler= Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
//		final String msg = ex.getLocalizedMessage();
		// Toast.makeText(mContext, "恭喜你，中奖了", Toast.LENGTH_SHORT).show();final
		// String msg = ex.getLocalizedMessage();
		// 使用Toast来显示异常信息
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				MyLog.d("恭喜你，中奖了");
				// Toast.makeText(mContext, "恭喜你，中奖了",
				// Toast.LENGTH_SHORT).show();
				Looper.loop();
			}

		}.start();

		// ClientStatistics.statisticsPageCrash(mContext, true, msg);
		// //如果用户没有处理则让系统默认的异常处理器来处理
		// Sleep一会后结束程序
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
		// Intent intent = new Intent(
		// ActivityUtil.INTENT_ACTION_LOGGED_OUT);
		// SuningEBuyForPadApplication.getInstance()
		// .setmGlobleUserId(null);
		// mContext.sendBroadcast(intent);
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
