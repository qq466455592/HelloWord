package com.mobile.meishang.core.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.network.DataProtocol;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkManager;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.utils.FunctionUtil;

public abstract class HttpRequestLoader<D> extends AsyncTaskLoader<D> implements
		DataProtocol<D> {
	private D mData;
	private ZLNetworkRequest httpRequest;
	private ExceptionHandler exceptionHandler;
	private int identity;
	private Context mContext;

	public HttpRequestLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context);
		mContext = context;
		this.httpRequest = _HttpRequest;
		this.identity = this.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public D loadInBackground() {
		if (FunctionUtil.isConnect(mContext)) {
			try {
				// return (D) ZLNetworkManager.Instance().perform(httpRequest,
				// this, null, false);
				return (D) ZLNetworkManager.Instance().perform(httpRequest,
						this);
			} catch (ZLNetworkException e) {
				handleException(identity, e);
			}
		} else {
			handleException(identity, new ZLNetworkException(
					ZLNetworkException.NETWORK_DISCONNECT));

		}
		return null;
	}

	/**
	 * 当有一个新的值提交到客户端时调用此方法 在父类中将维护这个提交值; 在这里重写处理一些资源释放的逻辑
	 */
	@Override
	public void deliverResult(D data) {
		if (isReset()) {

			if (data != null) {
				onReleaseResources(data);
			}
		}
		D oldData = mData;
		mData = data;

		if (isStarted()) {
			super.deliverResult(data);
		}

		if (oldData != null) {
			onReleaseResources(oldData);
		}
	}

	/**
	 * 处理启动的请求
	 */
	@Override
	protected void onStartLoading() {
		if (mData != null) {
			// 如果存在一个有效的结果就立即提交它
			deliverResult(mData);
		}

		if (takeContentChanged() || mData == null) {
			// 从最后已经加载的时间开始有改变或者当前的交付值无效则启动一个加载器
			forceLoad();
		}
	}

	/**
	 * 处理一个停止加载的请求
	 */
	@Override
	protected void onStopLoading() {
		// 取消当前加载任务
		cancelLoad();
	}

	/**
	 * 处理取消加载
	 */
	@Override
	public void onCanceled(D data) {
		super.onCanceled(data);

		// 释放资源 （如果需要）
		onReleaseResources(data);
	}

	/**
	 * 处理全部重置
	 */
	@Override
	protected void onReset() {
		super.onReset();

		// 确保加载器已经停止
		onStopLoading();

		// 释放资源 （如果需要）
		if (mData != null) {
			onReleaseResources(mData);
			mData = null;
		}
	}

	/**
	 * 释放资源
	 */
	protected void onReleaseResources(D data) {
		// 处理资源释放 如 cursor 和 流 的关闭
	}

	protected void handleException(int identity, Exception e) {
		if (null != exceptionHandler) {

			exceptionHandler.handleException(identity, e);

		}
	}

	public ZLNetworkRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(ZLNetworkRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public ExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentit(int identit) {
		this.identity = identit;
	}

}
