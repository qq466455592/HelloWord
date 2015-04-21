package com.mobile.meishang.core.network;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public interface DataProtocol<D> {
	public D handle(String content) throws ZLNetworkException;
}
