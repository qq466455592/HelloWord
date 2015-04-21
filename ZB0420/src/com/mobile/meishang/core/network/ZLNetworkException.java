/*
 * Copyright (C) 2010-2012 Geometer Plus <contact@geometerplus.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package com.mobile.meishang.core.network;


/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public class ZLNetworkException extends Exception {
	public static final String ERROR_CONNECT_TO_HOST = "couldntConnectMessage";// 连接不上服务器
	public static final String NETWORK_DISCONNECT = "networkdisconnect";// 网络已经断开
	public static final String NETWORK_TIME_OUT = "netWorkTimeOut";// 网络超时
	// Messages with jsonparser exception
	public static final String SERVER_ERROR = "servererror";// 服务器内部错误
	public static final String ERROR_JSONPARSER = "jsonparser";// json解析异常
	public static final String SIGN_ERROR = "signerror";// sign错误
	private static final long serialVersionUID = 4272384299121648643L;// 标志
	final private String myCode;// 编码

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	private static String geterrorMsgByKey(String key) {

		// if (LeShiHuiApplication.context == null) {
		// return "";
		// }
		// if (key.equals(ERROR_CONNECT_TO_HOST)) {
		// return LeShiHuiApplication.context
		// .getString(R.string.couldntConnectMessage);
		//
		// } else if (key.equals(ERROR_JSONPARSER)) {
		// return LeShiHuiApplication.context.getString(R.string.jsonerror);
		// } else if (key.equals(NETWORK_TIME_OUT)) {
		// return LeShiHuiApplication.context
		// .getString(R.string.netWorkTimeOut);
		// } else if (key.equals(SERVER_ERROR)) {
		// return LeShiHuiApplication.context.getString(R.string.server_error);
		// } else if (key.equals(NETWORK_DISCONNECT)) {
		// return LeShiHuiApplication.context
		// .getString(R.string.netWorkDisconnect);
		// }

		if (key.equals(ERROR_CONNECT_TO_HOST)) {
			return "联网错误，请检查网络";

		} else if (key.equals(ERROR_JSONPARSER)) {
			return "网络异常，请稍后再试";
		} else if (key.equals(NETWORK_TIME_OUT)) {
			return "联网超时，请稍后再试";
		} else if (key.equals(SERVER_ERROR)) {
			return "服务器正在升级，请稍后再试";
		} else if (key.equals(NETWORK_DISCONNECT)) {
			return "网络已断开，请检查网络";
		} else if (key.equals(SIGN_ERROR)) {
			return "签名错误";
		}
		return "";

	}

	private static String errorMessage(String key) {
		if (key == null) {
			return "null";
		}
		return geterrorMsgByKey(key);
	}

	public ZLNetworkException(boolean useAsMessage, String str, Throwable cause) {
		super(useAsMessage ? str : errorMessage(str), cause);
		myCode = useAsMessage ? null : str;
	}

	public ZLNetworkException(boolean useAsMessage, String str) {
		super(useAsMessage ? str : errorMessage(str));
		myCode = useAsMessage ? null : str;
	}

	public ZLNetworkException(String code, Throwable cause) {
		this(false, code, cause);
	}

	public ZLNetworkException(String code) {
		this(false, code);
	}

	public String getCode() {
		return myCode;
	}
}
