package com.mobile.meishang;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.mobile.meishang.core.error.ExceptionHandler;

public abstract class MFragment extends Fragment implements ExceptionHandler {
	protected int mCurrentDensity = 0;
	protected int mCurrentWidthPixels = 0;
	protected int mCurrentHeightPixels = 0;
	protected Bundle mBundle;
	protected Context mContext;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = getActivity();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (mCurrentWidthPixels == 0) {
			DisplayMetrics displayMetrics = new DisplayMetrics();
			((WindowManager) getActivity().getApplicationContext()
					.getSystemService(Context.WINDOW_SERVICE))
					.getDefaultDisplay().getMetrics(displayMetrics);
			mCurrentDensity = (int) displayMetrics.density;
			mCurrentWidthPixels = displayMetrics.widthPixels;
			mCurrentHeightPixels = displayMetrics.heightPixels;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	public abstract void updateUI(int identity, Object data);

	public abstract void resetUI(int identity, Object data);

	@Override
	public void handleException(int identity, Exception e) {

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
			getActivity().overridePendingTransition(enterAnim, exitAnim);
	}

	protected void goActivity(Class<?> actvityName, Bundle bundle) {
		Intent intent = new Intent(mContext, actvityName);
		intent.putExtra("bundle", bundle);
		startActivity(intent);

	}

	protected void goActivityForResult(Class<?> actvityName, Bundle bundle,
			int requestCode) {
		Intent intent = new Intent(mContext, actvityName);
		intent.putExtra("bundle", bundle);
		startActivityForResult(intent, requestCode);

	}

	protected void goActivityFadeInOut(Class<?> actvityName, Bundle bundle) {
		Intent intent = new Intent(mContext, actvityName);
		intent.putExtra("bundle", bundle);
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.activity_fade_in,
				R.anim.activity_fade_out);

	}

	protected void goActivityUpInOut(Class<?> actvityName, Bundle bundle) {
		Intent intent = new Intent(mContext, actvityName);
		intent.putExtra("bundle", bundle);
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.push_top_in,
				R.anim.push_top_out);

	}

	protected String getTime() {
		return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)
				.format(new Date());
	}

	/**
	 * 显示Toast
	 * 
	 * @param content
	 */
	protected void showToast(String showText) {
		Toast.makeText(getActivity(), showText, Toast.LENGTH_SHORT).show();
	}
}