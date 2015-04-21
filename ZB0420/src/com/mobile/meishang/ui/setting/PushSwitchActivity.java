package com.mobile.meishang.ui.setting;
//package com.ivpoints.mobile.ui.setting;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//import android.widget.TextView;
//import android.widget.ToggleButton;
//
//import com.ivpoints.mobile.LeShiHuiActivity;
//import com.ivpoints.mobile.R;
//import com.umeng.analytics.MobclickAgent;
//
//public class PushSwitchActivity extends LeShiHuiActivity {
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_push_switch);
//		findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
//		TextView title = (TextView) findViewById(R.id.top_name);
//		title.setText("推送开关");
//		title.setVisibility(View.VISIBLE);
//		ToggleButton messagePush = (ToggleButton) findViewById(R.id.switch_message_push_btn);
//		messagePush.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView,
//					boolean isChecked) {
//				buttonView.setChecked(isChecked);
//			}
//		});
//		ToggleButton versionUpdate = (ToggleButton) findViewById(R.id.switch_version_update_btn);
//		versionUpdate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView,
//					boolean isChecked) {
//				buttonView.setChecked(isChecked);
//			}
//		});
//	}
//
//	@Override
//	public void onResume() {
//		super.onResume();
//		MobclickAgent.onResume(this);
//	}
//
//	@Override
//	public void onPause() {
//		super.onPause();
//		MobclickAgent.onPause(this);
//	}
// }
