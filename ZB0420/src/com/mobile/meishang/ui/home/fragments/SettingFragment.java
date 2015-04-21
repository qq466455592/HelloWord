package com.mobile.meishang.ui.home.fragments;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.core.request.LogoutRequest;
import com.mobile.meishang.imagecache.DiskLruCache;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.VersionUpdate;
import com.umeng.analytics.MobclickAgent;

public class SettingFragment extends MFragment implements OnClickListener {
	private String mFilesName = "images";// 缓存图片的文件夹名称
	private File mDiskCacheDir;// 用于得到图片缓存路径
	private TextView mVersionUpdate;
	private TextView mLogout;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setting, null);
		view.findViewById(R.id.top_layout_back).setVisibility(View.GONE);
		TextView title = (TextView) view.findViewById(R.id.top_name);
		title.setText(R.string.setting_fragment);
		title.setVisibility(View.VISIBLE);
		view.findViewById(R.id.feedback).setOnClickListener(this);
		view.findViewById(R.id.clear_cache).setOnClickListener(this);
		// view.findViewById(R.id.push_switch_tv).setOnClickListener(this);
		mVersionUpdate = (TextView) view.findViewById(R.id.version_update);
		mVersionUpdate.setOnClickListener(this);

		view.findViewById(R.id.newbie_help).setOnClickListener(this);
		view.findViewById(R.id.about_us).setOnClickListener(this);
		view.findViewById(R.id.service_tel).setOnClickListener(this);
		mLogout = (TextView) view.findViewById(R.id.logout);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mDiskCacheDir = DiskLruCache.getDiskCacheDir(getActivity(), mFilesName);
		if (isExistFile(mDiskCacheDir))// 判断文件夹及其路径是否存在
		{
			if (isEmptyFile(mDiskCacheDir))// 判断缓存图片文件夹是否为空
			{
				// buttonInvalidState();// 按钮处理
			}

		} else {
			mDiskCacheDir = DiskLruCache.getInternalDir(getActivity(),
					mFilesName);
			if (isEmptyFile(mDiskCacheDir))// 判断缓存图片文件夹是否为空
			{
				// buttonInvalidState();// 按钮处理
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(mContext);
		// if (FunctionUtil.isUpdateVersion(mContext)) {
		// mVersionUpdate.setText("版本更新（点击更新）");
		// }
		// if (TextUtils.isEmpty(MApplication.getSessionId())) {
		// mLogout.setVisibility(View.GONE);
		// } else {
		// mLogout.setVisibility(View.VISIBLE);
		// mLogout.setOnClickListener(this);
		// }
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(mContext);
	}

	@Override
	public void handleException(int identity, Exception e) {

	}

	@Override
	public void updateUI(int identity, Object data) {

		switch (identity) {
		case RequestDistribute.LOGOUT:
			// Head requestResponseInfo = (Head) data;
			// if ("".equals(requestResponseInfo.getErrorCode())) {
			// // LeShiHuiApplication.setSessionId(requestResponseInfo
			// // .getSessionId());
			// MApplication.setSessionId("");
			// mLogout.setVisibility(View.GONE);
			//
			// showToast("注销成功");
			// } else {
			// showToast(requestResponseInfo.getErrorMessage());
			// }

			break;

		default:
			break;
		}
	}

	@Override
	public void resetUI(int identity, Object data) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.feedback:
			// goActivity(FeedbackActivity.class, null);
			break;
		case R.id.clear_cache:
			clearImages(mDiskCacheDir);// 删除缓存图片
			break;
		// case R.id.push_switch_tv:
		// goActivity(PushSwitchActivity.class, null);
		// break;
		case R.id.version_update:
			new VersionUpdate((MActivity) getActivity());
			break;
		case R.id.newbie_help:
			// goActivity(NewbieHelpActivity.class, null);
			break;
		case R.id.about_us:
			// goActivity(AboutUsActivity.class, null);
			break;
		case R.id.service_tel:
			Intent intent = new Intent(Intent.ACTION_DIAL,
					Uri.parse("tel:4000067900"));
			startActivity(intent);
			break;
		case R.id.logout:
			getLoaderManager().restartLoader(RequestDistribute.LOGOUT, null,
					new LogoutRequest(this));
			break;
		default:
			break;
		}

	}

	/**
	 * 图片文件夹路径是否存在
	 * 
	 * @Description:
	 * @Author 12071520
	 * @Date 2013-6-26
	 */
	private Boolean isExistFile(File diskCacheDir) {
		if (diskCacheDir.exists() && diskCacheDir.isDirectory()) {
			return true;
		}
		return false;
	}

	/**
	 * 缓存图片文件夹是否为空
	 * 
	 * @Description:
	 * @Author 12071520
	 * @Date 2013-6-26
	 */
	private Boolean isEmptyFile(File diskCacheDir) {
		if (diskCacheDir.listFiles().length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 清除图片缓存
	 * 
	 * @Description:
	 * @Author 12071520
	 * @Date 2013-6-26
	 */
	private void clearImages(File diskCacheDir) {
		DiskLruCache.clearCache(diskCacheDir);
		showToast("缓存图片清除成功");
		;
	}

}
