/**
 *@Copyright:Copyright (c) 2012 - 2100
 *@Company:suning.com
 */
package com.mobile.meishang.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.utils.view.DownProgressBarDialog;

public class VersionUpdate {
	private String APK_NAME = "leshihui.apk";
	private MActivity mContext;
	private DownProgressBarDialog mDownProgressBarDialog;
	private Thread mThread;

	/**
	 * 
	 * @param context
	 */
	public VersionUpdate(MActivity context) {
		mContext = context;
		if (FunctionUtil.isUpdateVersion(context)) {
			displayUpdateAppDialog();
		}
	}

	private void displayUpdateAppDialog() {
		final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
		dialog.setCancelable(false);
		dialog.show();
		Window window = dialog.getWindow();// *** 主要就是在这里实现这种效果的.//
		window.setContentView(R.layout.dialog_exit);
		TextView content = (TextView) window.findViewById(R.id.tv_content);
		content.setText("发现新版本，现在更新吗");
		Button cancelBtn = (Button) window.findViewById(R.id.btn_a);
		cancelBtn.setText("取消");
		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		Button exitBtn = (Button) window.findViewById(R.id.btn_b);
		exitBtn.setText("更新");
		exitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				boolean isHaveSDCard = Environment.getExternalStorageState()
						.equals(Environment.MEDIA_MOUNTED);
				if (isHaveSDCard) {
					downLoadApk();
				} else {
					Toast.makeText(mContext, "请插入SD卡再进行更新操作哦！",
							Toast.LENGTH_SHORT).show();
					// Timer timer = new Timer();
					// timer.schedule(new TimerTask() {
					// @Override
					// public void run() {
					// Intent intent = new Intent(
					// ActivityUtil.INTENT_ACTION_LOGGED_OUT);
					// LeShiHuiApplication.getInstance().setmGlobleUserId(
					// null);
					// mContext.sendBroadcast(intent);
					// }
					// }, 3000);

				}

			}
		});

	}

	private void downLoadApk() {

		mDownProgressBarDialog = new DownProgressBarDialog(mContext);
		mDownProgressBarDialog.setMessage("正在下载更新");
		// mDownProgressBarDialog.setCancelable(false);
		mDownProgressBarDialog.show();

		try {
			downloadFile("http://app.suning.com/d.php?pack=com.suning.pad.ebuy");
		} catch (Exception e) {
		}

	}

	private void downloadFile(final String urlString) throws Exception {

		mThread = new Thread() {
			public void run() {
				FileOutputStream fos = null;
				BufferedInputStream bis = null;
				try {
					HttpClient client = new DefaultHttpClient();
					HttpGet get = new HttpGet(urlString);
					HttpResponse response = client.execute(get);
					HttpEntity entity = response.getEntity();
					int totalsize = (int) entity.getContentLength();
					if (0 != totalsize) {
						mDownProgressBarDialog.setMax(totalsize);
					}
					InputStream in = entity.getContent();
					File file = null;
					if (null != in) {
						file = new File(
								Environment.getExternalStorageDirectory(),
								APK_NAME);
						fos = new FileOutputStream(file);
						bis = new BufferedInputStream(in);
						byte[] buf = new byte[1024];
						int ch = -1;
						int currentSize = 0;
						while ((ch = bis.read(buf)) != -1) {
							fos.write(buf, 0, ch);
							currentSize += ch;
							mDownProgressBarDialog.setProgress(currentSize);
						}
						if (null != mDownProgressBarDialog) {

							mDownProgressBarDialog.dismiss();
						}
						in.close();
						fos.flush();
					}
					install(mContext);

				} catch (Exception e) {
					mContext.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(mContext, "网络异常，请检查网络",
									Toast.LENGTH_LONG).show();
						}
					});
					mDownProgressBarDialog.dismiss();

				} finally {
					if (fos != null) {
						try {
							fos.close();
						} catch (IOException e) {
						}
					}

					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
						}
					}
				}
			}
		};
		mThread.start();
	}

	private void install(Context context) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(), APK_NAME)),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	// private void displayNetWorkErrorExitAppDialog() {
	// View.OnClickListener posListener = new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// Intent intent = new Intent("/");
	// ComponentName cm = new ComponentName("com.android.settings",
	// "com.android.settings.Settings");
	// intent.setComponent(cm);
	// intent.setAction("android.intent.action.VIEW");
	// mContext.startActivity(intent);
	// mContext.finish();
	// }
	// };
	// View.OnClickListener negListener = new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// if (isConnect(mContext)) {
	// downLoadApk();
	// } else {
	// mContext.finish();
	// }
	// }
	// };
	// // IDialogAccessor mNetworkErrorExitDialogAccessor = AlertUtil
	// // .registerMutableDialogAccessor(mContext, posListener,
	// // negListener, null);
	// // AlertUtil.displayTitleMessageDialog(mContext,
	// // mNetworkErrorExitDialogAccessor, mContext.getResources()
	// // .getString(R.string.pub_title), mContext.getResources()
	// // .getString(R.string.network_setting), mContext
	// // .getResources().getString(R.string.pub_settting),
	// // mContext.getResources().getString(R.string.pub_cancel), 0);
	// }
	//
	// public boolean isConnect(Context context) {
	// try {
	// ConnectivityManager connectivity = (ConnectivityManager) context
	// .getSystemService(Context.CONNECTIVITY_SERVICE);
	// if (connectivity != null) {
	// NetworkInfo info = connectivity.getActiveNetworkInfo();
	// if (info != null && info.isConnected()) {
	// if (info.getState() == NetworkInfo.State.CONNECTED) {
	// return true;
	// }
	// }
	// }
	// } catch (Exception e) {
	// return false;
	// }
	// return false;
	// }

}
