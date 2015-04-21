package com.mobile.meishang.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.config.Constants;
import com.mobile.meishang.logger.MyLog;

public class FunctionUtil {
	static final String TERMINAL_TYPE = "leshihui";

	public static String formatePrice(String price) {
		if (price == null || price.length() < 1 || price.equals("null")) {

			MyLog.jw("FunctionUtil",
					new Throwable("Format Price String Error!"));
			return "0";
		}
		String temp = formatPriceString(price, 3, 2, ',');
		if (temp.startsWith("-,")) {
			temp = temp.replace("-,", "-");
		}
		return temp;
	}

	/**
	 * 
	 * @Description:价格格式化
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	public static String formatPriceString(final String price,
			final int partitionNumbers, final int floatNumbers,
			final char partitionSymbol) {
		if (price == null || price.length() < 1 || partitionNumbers <= 0
				|| floatNumbers < 0 || partitionSymbol <= 0) {
			throw new RuntimeException("Format Price String Error!");
		}

		boolean hasDot = false;

		StringBuffer sb = new StringBuffer();

		int dotIndex = price.indexOf('.');

		if (dotIndex == -1) {
			dotIndex = price.length();
		} else {
			hasDot = true;
		}

		int firstCommaIndex = dotIndex - partitionNumbers;
		if (firstCommaIndex <= 0) {
			if (hasDot) {
				int retLen = dotIndex + floatNumbers;
				int priceLen = price.length() - 1;
				if (floatNumbers == 0) {
					sb.append(price.substring(0, dotIndex));
				} else if (retLen < priceLen) {
					// 这边做一下小数点第三位的四舍五入
					String num = price.substring(dotIndex + 3, dotIndex + 4);
					if (Integer.parseInt(num) > 4) {
						BigDecimal x = new BigDecimal(price.substring(0,
								retLen + 1));
						BigDecimal y = new BigDecimal("0.01");
						sb.append(x.add(y).toString());
					} else {
						sb.append(price.substring(0, retLen + 1));
					}

				} else if (retLen >= priceLen) {
					sb.append(price);
					for (int i = 0; i < retLen - priceLen; i++) {
						sb.append('0');
					}
				}
			} else if (floatNumbers > 0) {
				sb.append(price);
				sb.append('.');
				for (int i = 0; i < floatNumbers; i++) {
					sb.append('0');
				}
			} else {
				sb.append(price);
			}

			return sb.toString();
		}

		while (firstCommaIndex > 0) {
			firstCommaIndex -= partitionNumbers;
		}

		int startPos = 0;

		while ((firstCommaIndex += partitionNumbers) < dotIndex) {
			sb.append(price.substring(startPos, firstCommaIndex));
			sb.append(partitionSymbol);
			startPos = firstCommaIndex;
		}

		sb.append(price.substring(startPos, dotIndex));

		if (hasDot) {
			int retLen = dotIndex + floatNumbers;
			int priceLen = price.length() - 1;
			if (floatNumbers == 0) {
				// just fall down.
			} else if (retLen <= priceLen) {
				sb.append(price.substring(dotIndex, retLen + 1));
			} else if (retLen > priceLen) {
				sb.append(price.substring(dotIndex, priceLen + 1));
				int len = retLen - priceLen;

				for (int i = 0; i < len; i++) {
					sb.append('0');
				}
			}
		} else if (floatNumbers > 0) {
			sb.append('.');
			for (int i = 0; i < floatNumbers; i++) {
				sb.append('0');
			}
		}

		return sb.toString();
	}

	/**
	 * formatMoney
	 * 
	 * @Description:
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	public static String formatMoney(String money) {

		String formatMoney = String.valueOf(Float.parseFloat(money) / 100.00);
		String style = "###,###,##0.00";
		DecimalFormat df = new DecimalFormat(style);
		return df.format(Float.parseFloat(formatMoney)) + "Ԫ";
	}

	// /**
	// * 构造用于post网络访问的JSON对象
	// *
	// * @param params
	// * 存放参数的键值对
	// * @return　JSON 的string格式
	// * @throws HttpException
	// */
	// public static String getRequestJson(List<NameValuePair> params) {
	// JSONStringer js = new JSONStringer();
	// try {
	// js.object();
	// for (int i = 0; i < params.size(); i++) {
	// js.key(params.get(i).getName()).value(params.get(i).getValue());
	// }
	// js.endObject();
	// } catch (JSONException e) {
	// MyLog.e(e.getMessage());
	// return null;
	// }
	// return js.toString();
	// }
	/**
	 * long类型时间格式化
	 */
	public static String formatTime(long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time);
		return df.format(date);
	}

	/**
	 * 
	 * @Description:时间格式化方法
	 */
	public static String formatTime(String time) {
		if (time == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		Date date = new Date(time);
		return sdf.format(date).toString();
	}

	/**
	 * 
	 * @Description:生成当前时间
	 */
	public static String currentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date()).toString();
	}

	/**
	 * 单位转换
	 * 
	 * @Description:
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 单位转换
	 * 
	 * @Description:
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 
	 * @Description:缩放bitmap方法
	 * @Author 11076392
	 * @Date 2012-6-21
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, float scale) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		return newbmp;
	}

	/**
	 * 
	 * @Description:MD5加密方法
	 * @Author 11076392
	 * @Date 2012-6-21
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			MyLog.e(e.getMessage());
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	// MD5加密，32位
	public static String MD5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	// 可逆的加密算法
	public static String encryptmd5(String str) {
		char[] a = str.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 'l');
		}
		String s = new String(a);
		return s;
	}

	/**
	 * 
	 * @Description:判断网络连接是否可用
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	public static boolean isActiveNetwork(Context context) {
		boolean flag = false;
		if (null != context) {
			ConnectivityManager mConnMgr = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (null != mConnMgr) {
				NetworkInfo aActiveInfo = mConnMgr.getActiveNetworkInfo();
				if (null != aActiveInfo && aActiveInfo.isAvailable()) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * @Description: 图片内存释放
	 * @Author 11076392
	 * @Date 2012-6-21
	 */
	public static void recycle(Bitmap[] bitmaps) {
		for (int i = 0; i < bitmaps.length; i++) {
			if (bitmaps[i] != null && !bitmaps[i].isRecycled()) {
				bitmaps[i].recycle();
				bitmaps[i] = null;
			}
		}
	}

	/**
	 * 手机IP
	 * 
	 * @Description:
	 * @Author 11112760
	 * @Date 2012-8-7
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			MyLog.e(ex.getMessage());
		}
		return null;
	}

	public static String getNetType(Context context) {
		String netType = null;
		if (null != context) {
			ConnectivityManager conn = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (null != conn) {
				NetworkInfo info = conn.getActiveNetworkInfo();
				if (null != info && info.isAvailable() && info.isConnected()) {
					String type = info.getTypeName().toLowerCase();// MOBILE��GPRS��;WIFI
					if (null != type) {

						if ("mobile".equals(type)) {
							String apn = info.getExtraInfo();
							if (null != apn) {
								apn = apn.toLowerCase();
								if ("cmwap".equals(apn) || "3gwap".equals(apn)
										|| "uniwap".equals(apn)) {
									netType = "wap";
								} else {
									netType = "net";
								}
							}
						} else if ("wifi".equals(type)) {
							netType = "wifi";
						}
					}
				}
			}
		}
		return netType;
	}

	/**
	 * According to the specified time every minute digital time format is
	 * converted to type String
	 * 
	 * @param time
	 * @return
	 */
	public static String[] getTimer(int[] time) {
		String[] timeStr = new String[time.length];

		for (int i = 0; i < timeStr.length; i++) {
			if (i != 3 && time[i] < 10) {
				timeStr[i] = "0" + time[i];
			} else {
				timeStr[i] = "" + time[i];
			}
		}
		return timeStr;

	}

	/**
	 * The snapped start how long
	 * 
	 * @param first
	 * @param after
	 * @return
	 */
	public static long getBetweenTime(String first, String after) {
		long betweenTime = 0;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = format.parse(first);
			Date date2 = format.parse(after);
			betweenTime = date2.getTime() - date1.getTime();
		} catch (Exception e) {
			MyLog.e(e.getMessage());
		}
		return betweenTime;
	}

	/**
	 * 
	 * @throws JSONException
	 * @Description:json解析
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	public static String getJsonStrValue(JSONObject json, String name)
			throws JSONException {
		if (json.has(name)) {
			return json.getString(name);
		}
		return "";
	}

	/**
	 * 
	 * @throws JSONException
	 * @Description:json解析
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	public static int getJsonIntValue(JSONObject json, String name)
			throws JSONException {
		if (json.has(name)) {
			return json.getInt(name);
		}
		return -1;
	}

	public static JSONObject getJsonObject(JSONObject json, String name)
			throws JSONException {
		if (json.has(name)) {
			return json.getJSONObject(name);
		}
		return null;
	}

	public static JSONArray getJsonArray(JSONObject jsonObject, String name)
			throws JSONException {
		if (jsonObject.has(name)) {
			return jsonObject.getJSONArray(name);
		}
		return null;
	}

	/**
	 * 
	 * @Description:全角转半角
	 */
	public static String toDBC(String input) {
		if (null != input && !"".equals(input)) {

			char[] c = input.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == 12288) {
					c[i] = (char) 32;
					continue;
				}
				if (c[i] > 65280 && c[i] < 65375)
					c[i] = (char) (c[i] - 65248);
			}
			return new String(c);
		}
		return "";
	}

	/**
	 * 
	 * @Description:图片进行平铺
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	public static Bitmap createRepeater(int width, Bitmap src) {
		int count = (width + src.getWidth() - 1) / src.getWidth();

		Bitmap bitmap = Bitmap.createBitmap(width, src.getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);

		for (int idx = 0; idx < count; ++idx) {

			canvas.drawBitmap(src, idx * src.getWidth(), 0, null);
		}

		return bitmap;
	}

	/**
	 * 
	 * @Description:htmlfrom
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	public static CharSequence htmlfrom(CharSequence text) {
		Pattern htmlflag1 = Pattern.compile("<(.*?)>");
		SpannableStringBuilder builder = new SpannableStringBuilder(text);
		Matcher matcher = htmlflag1.matcher(text);
		while (matcher.find()) {
			builder.delete(matcher.start(), matcher.end());
			text = builder;
			matcher = htmlflag1.matcher(text);
		}
		Pattern htmlflag2 = Pattern.compile("&(.*?);");
		matcher = htmlflag2.matcher(text);
		while (matcher.find()) {
			builder.delete(matcher.start(), matcher.end());
			text = builder;
			matcher = htmlflag2.matcher(text);
		}

		return builder;

	}

	/**
	 * 
	 * @Description:控制editText的输入长度
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	public static TextWatcher myTextWatcher(final EditText view,
			final int length) {
		return new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				int num = s.toString().length();
				if (num > length) {
					s.delete(length, s.toString().length());
					view.setText(s);
					view.setSelection(view.getText().length());
					return;
				}
			}
		};
	}

	/**
	 * 
	 * @Description:get App Version Number
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	public static int getAppVersionCode(Context context) {
		try {
			final String PackageName = context.getPackageName();
			return context.getPackageManager().getPackageInfo(PackageName, 1).versionCode;
		} catch (NameNotFoundException e) {
			MyLog.e(e.getMessage());
		}
		return 0;
	}

	public static Boolean isUpdateVersion(Context context) {
		String newVersion = MApplication.getInstance().getmConfig()
				.getPreferencesVal(Constants.VERSION_CODE, "1");// 默认版本号是1
		float currentVersionCode = getAppVersionCode(context);
		float newVersionCode = -1;
		if (null != newVersion && !"".equals(newVersion)) {
			try {
				newVersionCode = Float.parseFloat(newVersion);
			} catch (NumberFormatException e) {
			}
		}
		if (newVersionCode > currentVersionCode) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * get Terminal type
	 */
	public static String getTerminalTypel(Context context) {
		return TERMINAL_TYPE;
	}

	/**
	 * get Telphone system Version
	 */
	public static String getTelphoneSysVersion(Context context) {
		String release = android.os.Build.VERSION.RELEASE;
		return release;
	}

	/**
	 * get Telphone models
	 */
	public static String getTelphoneModels(Context context) {
		String model = android.os.Build.MODEL;
		return model;
	}

	/**
	 * get Telphone imei
	 */
	public static String getTelphoneIMEI(Context context) {
		String imei = ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();

		if (imei == null || imei.equals("")) {
			// imei = LeShiHuiApplication.getInstance().getmConfig()
			// .getPhoneIMEI();
			// if (imei.equals("")) {
			// String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")
			// .format(new Date());
			// imei = getMD5Str(now);
			// LeShiHuiApplication.getInstance().getmConfig()
			// .setPhoneIMEI(imei);
			// }
		}
		return imei;
	}

	/**
	 * 获取当前设置的电话号码
	 */
	public String getNativePhoneNumber(Context context) {
		String NativePhoneNumber = null;
		NativePhoneNumber = ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
		return NativePhoneNumber;
	}

	/**
	 * get user ip
	 */
	public static String getUserIp(Context context) {
		// 获取wifi服务
		String ip;
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager.isWifiEnabled()) {
			// wifiManager.setWifiEnabled(true);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();
			ip = intToIp(ipAddress);
		} else {
			ip = getLocalIpAddress();
		}
		return ip;
	}

	/**
	 * 
	 * @Description:
	 * @Author 12071520
	 * @Date 2013-5-13
	 */
	private static String intToIp(int i) {

		return (i & 0xFF) + "." +

		((i >> 8) & 0xFF) + "." +

		((i >> 16) & 0xFF) + "." +

		(i >> 24 & 0xFF);

	}

	/**
	 * @Description:获取SIM卡运营商
	 * @Author 12073137
	 * @Date 2013-5-29
	 */
	public static String getSimOperatorName(Context context) {

		// 返回唯一的用户ID;卡的编号
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String IMSI = telephonyManager.getSubscriberId();

		// IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
		String ProvidersName = null;
		if (IMSI != null) {
			if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
				ProvidersName = "移动";
			} else if (IMSI.startsWith("46001")) {
				ProvidersName = "联通";
			} else if (IMSI.startsWith("46003")) {
				ProvidersName = "电信";
			} else {
				ProvidersName = "其他";
			}
			return ProvidersName;
		} else {
			return "无卡";
		}
	}

	/**
	 * @param context
	 *            Context 是上下文环境
	 * @return 客户端版本名字
	 */
	public static String getVerName(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
		}

		return "";

	}

	/**
	 * 列表载入时动画的公用controller
	 * 
	 * @return
	 */
	public static LayoutAnimationController getListAnimationController() {
		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(50);
		set.addAnimation(animation);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(100);
		set.addAnimation(animation);

		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.5f);
		return controller;
	}

	/**
	 * 验证手机号
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static boolean checkingPhone(String phoneNum) {
		if (!Pattern.compile("(1(([358*][0-9])|(47)|[8][0126789]))\\d{8}$")
				.matcher(phoneNum).matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkingEmail(String email) {
		if (!Pattern
				.compile(
						"^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")
				.matcher(email).find()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断网络连接
	 * 
	 * @Description:
	 * @Author 11120500
	 * @Date 2013-4-25
	 */
	public static boolean isConnect(Context context) {

		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			// 获取网络连接管理的对象
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected()) {
				// 判断当前网络是否已经连接
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 是否包含特殊字符
	 * 
	 * @param str
	 */
	public static boolean hasSpecialChar(String str) {
		boolean isSpecial = false;
		char[] addressChar = str.toCharArray();
		int s = addressChar.length;

		Pattern pAddress = Pattern
				.compile("[`~!$%^&*()+=|{}':;',\"\\[\\].<>/?~！￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]");
		for (int i = 0; i < s; i++) {
			if (pAddress.matcher(String.valueOf(addressChar[i])).matches()) {
				isSpecial = true;
			}
		}
		return isSpecial;
	}

}
