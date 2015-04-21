package com.mobile.meishang.ui.share;

import android.os.Bundle;
import android.view.View;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;

public class SharedActivity extends MActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared);
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.btn_sub:
			showToast("提交");
			// 首先在您的Activity中添加如下成员变量
			final UMSocialService mController = UMServiceFactory
					.getUMSocialService("com.ivpoints.mobile.finance");
			mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN,
					SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ,
					SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT,
					SHARE_MEDIA.DOUBAN, SHARE_MEDIA.RENREN);
			mController.openShare(this, false);

			// 设置分享内容
			mController
					.setShareContent("xxxxxxxxxxxxxxxxxxxxxx，http://www.test.com/social");
			// 设置分享图片, 参数2为图片的url地址
			mController
					.setShareMedia(new UMImage(this,
							"http://103.242.168.154:9001/BCLife/staffPhoto/goods/5.png"));
			break;

		default:
			break;
		}
	}
}
