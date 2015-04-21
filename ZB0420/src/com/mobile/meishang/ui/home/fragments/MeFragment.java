package com.mobile.meishang.ui.home.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.ui.favorites.FavoritesActivity;
import com.mobile.meishang.ui.login.LoginActivity;
import com.mobile.meishang.ui.login.RegisterActivity;
import com.mobile.meishang.ui.shopping.ShoppingCarListActivity;
import com.umeng.analytics.MobclickAgent;

public class MeFragment extends MFragment implements OnClickListener {
	private TextView tv_favorites_quantity;
	private TextView tv_attention_quantity;
	private TextView tv_msg_quantity;

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
		View view = inflater.inflate(R.layout.fragment_me, null);
		view.findViewById(R.id.top_layout_back).setVisibility(View.GONE);
		TextView title = (TextView) view.findViewById(R.id.top_name);
		title.setText("我的");
		view.findViewById(R.id.llayout_go_login).setOnClickListener(this);
		view.findViewById(R.id.tv_register).setOnClickListener(this);
		view.findViewById(R.id.llayout_favorites).setOnClickListener(this);
		view.findViewById(R.id.llayout_attention).setOnClickListener(this);
		view.findViewById(R.id.llayout_shopping_car).setOnClickListener(this);
		tv_favorites_quantity = (TextView) view
				.findViewById(R.id.tv_favorites_quantity);
		tv_attention_quantity = (TextView) view
				.findViewById(R.id.tv_attention_quantity);
		view.findViewById(R.id.llayout_msg).setOnClickListener(this);
		tv_msg_quantity = (TextView) view.findViewById(R.id.tv_msg_quantity);
		view.findViewById(R.id.tv_wallet).setOnClickListener(this);
		view.findViewById(R.id.tv_orders).setOnClickListener(this);
		view.findViewById(R.id.tv_wait_comment).setOnClickListener(this);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(mContext);
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
			Head requestResponseInfo = (Head) data;

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
		case R.id.llayout_go_login:
			goActivity(LoginActivity.class, null);
			break;
		case R.id.tv_register:
			goActivity(RegisterActivity.class, null);
			break;
		case R.id.llayout_favorites:
			showToast("功能尚在开发中");
			goActivity(FavoritesActivity.class, null);
			break;
		case R.id.llayout_attention:
			showToast("功能尚在开发中");
			// goActivity(LoginActivity.class, null);
			break;
		case R.id.llayout_shopping_car:
			goActivity(ShoppingCarListActivity.class, null);
			break;
		case R.id.llayout_msg:
			showToast("功能尚在开发中");
			// goActivity(LoginActivity.class, null);
			break;
		case R.id.tv_wallet:
			// if (MApplication.getInstance().checkLogin()) {
			// goActivity(MyVoucherActivity.class, null);
			// } else {
			// goActivityForResult(LoginActivity.class, null,
			// RequestDistribute.LOGIN);
			// }
			showToast("功能尚在开发中");
			// goActivity(MyWalletActivity.class, null);
			break;
		case R.id.tv_orders:
			// goActivity(MyWalletActivity.class, null);
			break;
		case R.id.tv_wait_comment:
			showToast("功能尚在开发中");
			// goActivity(LoginActivity.class, null);
			break;
		// case R.id.tv_seting:
		// showToast("功能尚在开发中");
		// // goActivity(LoginActivity.class, null);
		// break;
		// case R.id.tv_share:
		// showToast("功能尚在开发中");
		// // goActivity(LoginActivity.class, null);
		// break;
		// case R.id.clear_cache:
		// break;
		// case R.id.version_update:
		// new VersionUpdate((MActivity) getActivity());
		// break;
		// case R.id.newbie_help:
		// goActivity(NewbieHelpActivity.class, null);
		// break;
		// case R.id.about_us:
		// goActivity(AboutUsActivity.class, null);
		// break;
		// case R.id.service_tel:
		// Intent intent = new Intent(Intent.ACTION_DIAL,
		// Uri.parse("tel:4000067900"));
		// startActivity(intent);
		// break;
		// case R.id.logout:
		// getLoaderManager().restartLoader(RequestDistribute.LOGOUT, null,
		// new LogoutRequest(this));
		// break;
		default:
			break;
		}

	}

}
