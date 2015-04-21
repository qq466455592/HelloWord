package com.mobile.meishang.ui.ad;
//package com.ivpoints.mobile.life.ui.ad;
//
//import com.ivpoints.mobile.life.R;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//public class ExternalFragment extends Fragment {
//
//	private Bundle mBundle;
//	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.activity_alipay_external, container, false);
//
//		mBundle = getActivity().getIntent().getBundleExtra("bundle");
//		String goodsName = mBundle.getString("name");
//		String goodsDesp = mBundle.getString("desp");
//		String goodsPrice = mBundle.getString("price1");
//
//		TextView nameView = (TextView) view.findViewById(R.id.goodsName);
//		nameView.setText(goodsName );
//		TextView despView = (TextView) view.findViewById(R.id.goodsDesp);
//		despView.setText(goodsDesp );
//		TextView priceView = (TextView) view.findViewById(R.id.goodsPrice);
//		priceView.setText(goodsPrice+" 元" );
//		return view;
//	}
//	
// }
