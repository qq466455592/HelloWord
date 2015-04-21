package com.mobile.meishang;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.meishang.core.error.ExceptionHandler;

public class MActivity extends FragmentActivity implements ExceptionHandler {
	protected int mCurrentDensity = 0;
	protected int mCurrentWidthPixels = 0;
	protected int mCurrentHeightPixels = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (mCurrentWidthPixels == 0) {
			DisplayMetrics displayMetrics = new DisplayMetrics();
			((WindowManager) getApplicationContext().getSystemService(
					Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
					displayMetrics);
			mCurrentDensity = (int) displayMetrics.density;
			mCurrentWidthPixels = displayMetrics.widthPixels;
			mCurrentHeightPixels = displayMetrics.heightPixels;
		}
	}

	@Override
	public void handleException(int identity, Exception e) {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void updateUI(int identity, Object data) {
	}

	public void resetUI(int identity, Object data) {
	}

	public void setProgressDimiss() {
	}

	/**
	 * 自定义页面跳转效果
	 * 
	 * @Description:
	 * @Author 11120500
	 * @Date 2013-4-28
	 */
	protected void openWindow(Intent intent, int enterAnim, int exitAnim) {
		startActivity(intent);
		if (enterAnim != -1 && exitAnim != -1)
			overridePendingTransition(enterAnim, exitAnim);
	}

	protected void goActivity(Class<?> actvityName, Bundle bundle) {
		Intent intent = new Intent(this, actvityName);
		intent.putExtra("bundle", bundle);
		startActivity(intent);

	}

	protected void goActivityForResult(Class<?> actvityName, Bundle bundle,
			int requestCode) {
		Intent intent = new Intent(this, actvityName);
		intent.putExtra("bundle", bundle);
		startActivityForResult(intent, requestCode);

	}

	protected void goActivityFadeInOut(Class<?> actvityName, Bundle bundle) {
		Intent intent = new Intent(this, actvityName);
		intent.putExtra("bundle", bundle);
		startActivity(intent);
		overridePendingTransition(R.anim.activity_fade_in,
				R.anim.activity_fade_out);

	}

	protected void goActivityUpInOut(Class<?> actvityName, Bundle bundle) {
		Intent intent = new Intent(this, actvityName);
		intent.putExtra("bundle", bundle);
		startActivity(intent);
		overridePendingTransition(R.anim.push_top_in, R.anim.push_top_out);

	}

	/**
	 * 显示Toast
	 * 
	 * @param content
	 */
	protected void showToast(String showText) {
		Toast.makeText(this, showText, Toast.LENGTH_SHORT).show();
	}

	protected void exitDialog() {
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setCancelable(false);
		dialog.show();
		Window window = dialog.getWindow();// *** 主要就是在这里实现这种效果的.//
		window.setContentView(R.layout.dialog_exit);
		TextView content = (TextView) window.findViewById(R.id.tv_content);
		content.setText("确定退出乐时惠?");
		Button cancelBtn = (Button) window.findViewById(R.id.btn_a);
		cancelBtn.setText("取消");
		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		Button exitBtn = (Button) window.findViewById(R.id.btn_b);
		exitBtn.setText("退出");
		exitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				finish();
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(0);

			}
		});
	}

	protected String getTime() {
		return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)
				.format(new Date());
	}

	// 实现分享功能
	public void StartShareApp(Context context, String szChooserTitle,
			String title, String msg) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		// intent.setType("image/*");
		// intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
		// intent.putExtra(Intent.EXTRA_TEXT, "终于可以了!!!");
		// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, title);
		intent.putExtra(Intent.EXTRA_TEXT, msg);
		context.startActivity(Intent.createChooser(intent, szChooserTitle));
	}

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.top_layout_back:
			finish();
			break;
		// case R.id.top_layout_city:
		// goActivity(CityListActivity.class, null);
		// break;
		// case R.id.title_map:
		// goActivity(LocationOverlayActivity.class, null);
		// break;
		// case R.id.top_search:
		// goActivity(SearchActivity.class, null);
		// break;
		// case R.id.top_share:
		// StartShareApp(this, "分享到", "分享到", "我发现了一款很好的软件,很不错!赶快来试试哦！");
		// break;
		default:
			break;
		}
	}

	// protected void setNotification() {
	//
	// if (!LeShiHuiApplication.getInstance().getmConfig()
	// .getPreferencesVal(Constants.NOTYFICATION_SET, false)) { // 未开启
	// Log.i("leshihui", "--------消息推送-----------");
	// Intent notificationintent = new Intent(this, AlarmReceiver.class);
	// PendingIntent notificationintentpIntent = PendingIntent
	// .getBroadcast(this, 0, notificationintent, 0);
	// LeShiHuiApplication.getInstance().getmConfig()
	// .putPreferencesVal(Constants.NOTYFICATION_SET, true);
	// Log.i("leshihui", "----setNotification----startalarm-----------");
	// AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
	// Date date = new Date();
	// Log.i("leshihui", "---当前时间------" + date.toString());
	// long triggerAtTime = date.getTime();
	// long interval = 30 * 1000;
	// Log.i("leshihui", "---当前时间------" + triggerAtTime);
	// Log.i("leshihui", "---chongfu 时间------" + interval);
	// am.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtTime, interval,
	// notificationintentpIntent);
	// }
	// }

}