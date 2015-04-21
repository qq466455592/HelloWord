package com.mobile.meishang.core.network;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.util.ZLMiscUtil;
import com.mobile.meishang.core.util.ZLNetworkUtil;
import com.mobile.meishang.logger.MyLog;
import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.utils.FunctionUtil;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-4-27
 * @Version:
 */
public class ZLNetworkManager {
	private static ZLNetworkManager ourManager;

	// private static String sessionId;

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-4-29
	 */
	public static ZLNetworkManager Instance() {
		if (ourManager == null) {
			ourManager = new ZLNetworkManager();
		}
		return ourManager;
	}

	/**
	 * 
	 * @Title:
	 * @Description:
	 * @Author:Administrator
	 * @Since:2013-4-29
	 * @Version:
	 */
	private static class AuthScopeKey {
		private final AuthScope myScope;

		/**
		 * 
		 * @param scope
		 */
		public AuthScopeKey(AuthScope scope) {
			myScope = scope;
		}

		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof AuthScopeKey)) {
				return false;
			}

			final AuthScope scope = ((AuthScopeKey) obj).myScope;
			if (myScope == null) {
				return scope == null;
			}
			if (scope == null) {
				return false;
			}
			return myScope.getPort() == scope.getPort()
					&& ZLMiscUtil.equals(myScope.getHost(), scope.getHost())
					&& ZLMiscUtil
							.equals(myScope.getScheme(), scope.getScheme())
					&& ZLMiscUtil.equals(myScope.getRealm(), scope.getRealm());
		}

		public int hashCode() {
			if (myScope == null) {
				return 0;
			}
			return myScope.getPort() + ZLMiscUtil.hashCode(myScope.getHost())
					+ ZLMiscUtil.hashCode(myScope.getScheme())
					+ ZLMiscUtil.hashCode(myScope.getRealm());
		}
	}

	/**
	 * 
	 * @Title:
	 * @Description:
	 * @Author:11120500
	 * @Since:2013-4-25
	 * @Version:
	 */
	public static abstract class CredentialsCreator {
		final private HashMap<AuthScopeKey, Credentials> myCredentialsMap = new HashMap<AuthScopeKey, Credentials>();

		private volatile String myUsername;
		private volatile String myPassword;

		/**
		 * 
		 * @Description:
		 * @Author Administrator
		 * @Date 2013-4-29
		 */
		synchronized public void setCredentials(String username, String password) {
			myUsername = username;
			myPassword = password;
			release();
		}

		/**
		 * 
		 * @Description:
		 * @Author Administrator
		 * @Date 2013-4-29
		 */
		synchronized public void release() {
			notifyAll();
		}

		/**
		 * 
		 * @Description:
		 * @Author Administrator
		 * @Date 2013-4-29
		 */
		public Credentials createCredentials(String scheme, AuthScope scope,
				boolean quietly) {
			final String authScheme = scope.getScheme();
			if (!"basic".equalsIgnoreCase(authScheme)
					&& !"digest".equalsIgnoreCase(authScheme)) {
				return null;
			}

			final AuthScopeKey key = new AuthScopeKey(scope);
			Credentials creds = myCredentialsMap.get(key);
			if (creds != null || quietly) {
				return creds;
			}

			final String host = scope.getHost();
			final String area = scope.getRealm();
			// final ZLStringOption usernameOption =
			// new ZLStringOption("username", host + ":" + area, "");

			// 从配置项中取出保存的用户名
			if (!quietly) {
				startAuthenticationDialog(host, area, scheme, null);
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
					}
				}
			}

			if (myUsername != null && myPassword != null) {
				// usernameOption.setValue(myUsername);
				// 用户名保存到配置项
				creds = new UsernamePasswordCredentials(myUsername, myPassword);
				myCredentialsMap.put(key, creds);
			}
			myUsername = null;
			myPassword = null;
			return creds;
		}

		/**
		 * 
		 * @Description:
		 * @Author Administrator
		 * @Date 2013-4-29
		 */
		public boolean removeCredentials(AuthScopeKey key) {
			return myCredentialsMap.remove(key) != null;
		}

		/**
		 * 
		 * @Description:
		 * @Author Administrator
		 * @Date 2013-4-29
		 */
		abstract protected void startAuthenticationDialog(String host,
				String area, String scheme, String username);
	}

	private volatile CredentialsCreator myCredentialsCreator;

	/**
	 * 
	 * @Title:
	 * @Description:
	 * @Author:Administrator
	 * @Since:2013-4-27
	 * @Version:
	 */
	private class MyCredentialsProvider extends BasicCredentialsProvider {
		private final HttpUriRequest myRequest;
		private final boolean myQuietly;

		MyCredentialsProvider(HttpUriRequest request, boolean quietly) {
			myRequest = request;
			myQuietly = quietly;
		}

		@Override
		public Credentials getCredentials(AuthScope authscope) {
			final Credentials c = super.getCredentials(authscope);
			if (c != null) {
				return c;
			}
			if (myCredentialsCreator != null) {
				return myCredentialsCreator.createCredentials(myRequest
						.getURI().getScheme(), authscope, myQuietly);
			}
			return null;
		}
	};

	/**
	 * 
	 * @Title:
	 * @Description:
	 * @Author:Administrator
	 * @Since:2013-4-29
	 * @Version:
	 */
	private static class Key {
		final String Domain;
		final String Path;
		final String Name;

		Key(Cookie c) {
			Domain = c.getDomain();
			Path = c.getPath();
			Name = c.getName();
		}

		@Override
		public boolean equals(Object o) {
			if (o == this) {
				return true;
			}
			if (!(o instanceof Key)) {
				return false;
			}
			final Key k = (Key) o;
			return ZLMiscUtil.equals(Domain, k.Domain)
					&& ZLMiscUtil.equals(Path, k.Path)
					&& ZLMiscUtil.equals(Name, k.Name);
		}

		@Override
		public int hashCode() {
			return ZLMiscUtil.hashCode(Domain) + ZLMiscUtil.hashCode(Path)
					+ ZLMiscUtil.hashCode(Name);
		}
	};

	private final CookieStore myCookieStore = new CookieStore() {
		private HashMap<Key, Cookie> myCookies;

		public synchronized void addCookie(Cookie cookie) {
			if (myCookies == null) {
				getCookies();
			}
			myCookies.put(new Key(cookie), cookie);
			final CookieDatabase db = CookieDatabase.getInstance();
			if (db != null) {
				db.saveCookies(Collections.singletonList(cookie));
			}
		}

		/**
         * 
         */
		public synchronized void clear() {
			final CookieDatabase db = CookieDatabase.getInstance();
			if (db != null) {
				db.removeAll();
			}
			if (myCookies != null) {
				myCookies.clear();
			}
		}

		/**
         * 
         */
		public synchronized boolean clearExpired(Date date) {
			myCookies = null;

			final CookieDatabase db = CookieDatabase.getInstance();
			if (db != null) {
				db.removeObsolete(date);
				// TODO: detect if any Cookie has been removed
				return true;
			}
			return false;
		}

		/**
         * 
         */
		public synchronized List<Cookie> getCookies() {
			// BasicClientCookie cityIdCookie = null;
			ArrayList<Cookie> cookieList = new ArrayList<Cookie>();
			if (myCookies == null) {
				myCookies = new HashMap<Key, Cookie>();
				final CookieDatabase db = CookieDatabase.getInstance();
				if (db != null) {
					for (Cookie c : db.loadCookies()) {
						myCookies.put(new Key(c), c);
					}
				}
			} else {
				// if (myCookies.size() > 0) {
				//
				// cityIdCookie = new BasicClientCookie("cityId",
				// LeShiHuiApplication.getInstance().getmConfig()
				// .getCityCode());
				// }
			}
			// if (cityIdCookie != null) {
			// cookieList.add(cityIdCookie);
			//
			// }
			// LogEbuyPad.e("------myCookies-------"+myCookies.values());
			cookieList.addAll(myCookies.values());
			return cookieList;
		}
	};

	/*
	 * private void setCommonHTTPOptions(HttpMessage request) throws
	 * ZLNetworkException { httpConnection.setInstanceFollowRedirects(true);
	 * httpConnection.setAllowUserInteraction(true); }
	 */

	public void setCredentialsCreator(CredentialsCreator creator) {
		myCredentialsCreator = creator;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-4-29
	 */
	public CredentialsCreator getCredentialsCreator() {
		return myCredentialsCreator;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-4-29
	 */
	public Object perform(ZLNetworkRequest request) throws ZLNetworkException {
		return perform(request, null, null, false);

	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-4-27
	 */
	public Object perform(ZLNetworkRequest request,
			DataProtocol<?> mDataProtocol,
			ImageDataProtocol<?> mImageDataProtocol, boolean isImage)
			throws ZLNetworkException {
		boolean success = false;
		DefaultHttpClient httpClient = null;
		HttpEntity entity = null;
		try {
			final HttpContext httpContext = new BasicHttpContext();
			httpContext.setAttribute(ClientContext.COOKIE_STORE, myCookieStore);
			// StringBuffer buf = new StringBuffer("cityId=")
			// .append(SuningEBuyForPadApplication.getInstance()
			// .getmConfig().getCityCode()
			// + ";");
			// List<Cookie>
			// listCookies=httpClient.getCookieStore().getCookies();
			List<Cookie> listCookies = myCookieStore.getCookies();
			if (listCookies.isEmpty()) {
				MyLog.e("Cookie", "Cookie is Empty");
			} else {
				for (Cookie ck : listCookies) {
					MyLog.e("Cookie", ck.getName() + "--->" + ck.getValue());
					if ("JSESSIONID".equals(ck.getName())) {
						// sessionId = ck.getValue();
					}
				}
				// int size = listCookies.size();
				// for (int i = 0; i < size; i++) {
				// MyLog.e("Cookie", myCookieStore.getCookies().get(i).getName()
				// + "==" + myCookieStore.getCookies().get(i).getValue());
				//
				// // if (!listCookies.get(i).getName()
				// // .equals("WC_USERACTIVITY_-1002")
				// // && !listCookies.get(i).getValue().equals("DEL"))
				// // {
				// // buf.append(listCookies.get(i).getName()).append("=")
				// // .append(listCookies.get(i).getValue()).append(";");
				// // }
				// if (myCookieStore.getCookies().get(i).getName()
				// .equals("JSESSIONID")) {
				// sessionId = listCookies.get(i).getValue();
				// }
				// }
			}
			request.doBefore();
			final HttpParams params = new BasicHttpParams();
			String netType = FunctionUtil.getNetType(MApplication.getInstance()
					.getBaseContext());
			if (!TextUtils.isEmpty(netType)
					&& netType.toLowerCase(Locale.CHINA).equals("wap")) {
				HttpHost proxy = new HttpHost("10.0.0.172", 80);
				params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			} else {
				params.setParameter(ConnRoutePNames.DEFAULT_PROXY, null);
			}
			// HttpConnectionParams.setSoTimeout(params, 5000);
			// HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 1000 * 60 * 10);
			HttpConnectionParams.setConnectionTimeout(params, 20000);
			HttpClientParams.setRedirecting(params, true);
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", new SSLSocketFactoryEx(), 443));
			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);
			httpClient = new DefaultHttpClient(ccm, params);
			httpClient.setHttpRequestRetryHandler(requestRetryHandler);

			final HttpRequestBase httpRequest;
			MyLog.d("**********requestURL>>>>" + request.URL);
			if (request.PostData != null) {
				httpRequest = new HttpPost(request.URL);
				((HttpPost) httpRequest).setEntity(new StringEntity(
						request.PostData, "utf-8"));
				/*
				 * httpConnection.setRequestProperty( "Content-Length",
				 * Integer.toString(request.PostData.getBytes().length) );
				 * httpConnection.setRequestProperty( "Content-Type",
				 * "application/x-www-form-urlencoded" );
				 */
			} else if (!request.PostParameters.isEmpty()) {
				httpRequest = new HttpPost(request.URL);
				final List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>(
						request.PostParameters.size());
				StringBuffer fianlUrl = new StringBuffer(request.URL);
				for (Map.Entry<String, String> entry : request.PostParameters
						.entrySet()) {
					list.add(new BasicNameValuePair(entry.getKey(), entry
							.getValue()));
					fianlUrl.append(entry.getKey() + "=");
					fianlUrl.append(entry.getValue() + "&");
				}
				MyLog.d("fianlUrl=", fianlUrl.toString());
				((HttpPost) httpRequest).setEntity(new UrlEncodedFormEntity(
						list, "utf-8"));
			} else {
				httpRequest = new HttpGet(request.URL);
			}
			httpRequest.setHeader("User-Agent", ZLNetworkUtil.getUserAgent());
			if (!request.URL.endsWith("jcaptcha")) {
				httpRequest.setHeader("Accept-Encoding", "gzip");
			}
			httpRequest.setHeader("Accept-Language", Locale.getDefault()
					.getLanguage());
			httpRequest.getParams().setParameter("http.protocol.cooke-policy",
					CookiePolicy.BROWSER_COMPATIBILITY);
			// httpRequest.setHeader("Cookie", buf.toString());
			// httpRequest.setHeader("sessionid", sessionId);
			// MyLog.e("Cookie", "sessionid:" +
			// LeShiHuiApplication.getSessionId());
			// httpRequest.setHeader("sessionid",
			// LeShiHuiApplication.getSessionId());
			if (!request.heads.isEmpty()) {
				for (Map.Entry<String, String> entry : request.heads.entrySet()) {

					httpRequest.setHeader(entry.getKey(), entry.getValue());
				}
			}

			httpClient.setCredentialsProvider(new MyCredentialsProvider(
					httpRequest, request.isQuiet()));
			HttpResponse response = null;
			IOException lastException = null;
			for (int retryCounter = 0; retryCounter < 3 && entity == null; ++retryCounter) {
				try {
					response = httpClient.execute(httpRequest, httpContext);
					entity = response.getEntity();
					lastException = null;
					if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
						final AuthState state = (AuthState) httpContext
								.getAttribute(ClientContext.TARGET_AUTH_STATE);
						if (state != null) {
							final AuthScopeKey key = new AuthScopeKey(
									state.getAuthScope());
							if (myCredentialsCreator.removeCredentials(key)) {
								entity = null;
							}
						}
					}
				} catch (IOException e) {
					lastException = e;
				}
			}
			if (lastException != null) {
				throw lastException;
			}
			final int responseCode = response.getStatusLine().getStatusCode();
			MyLog.d("**********responseCode>>>>" + responseCode);
			InputStream stream = null;
			ByteArrayOutputStream outStream = null;
			if (entity != null && responseCode == HttpURLConnection.HTTP_OK) {
				BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(
						entity);

				stream = bufferedHttpEntity.getContent();
			}
			if (stream != null) {
				try {
					if (isImage) {
						MyLog.e("--------------------isImage---------");
						isImage = false;
						return mImageDataProtocol.handle(BitmapFactory
								.decodeStream(stream));
					} else {
						outStream = new ByteArrayOutputStream();
						final Header encoding = entity.getContentEncoding();
						if (encoding != null
								&& "gzip".equalsIgnoreCase(encoding.getValue())) {
							stream = new GZIPInputStream(stream);
						}
						if (mDataProtocol != null) {
							String outputStream = null;
							byte[] data = new byte[1024];
							int count = -1;
							while ((count = stream.read(data, 0, 1024)) != -1)
								outStream.write(data, 0, count);
							data = null;
							outputStream = new String(outStream.toByteArray(),
									HTTP.UTF_8);
							MyLog.d("**********response>>>>" + outputStream);
							return mDataProtocol.handle(outputStream);

						} else {
							request.handleStream(stream,
									(int) entity.getContentLength());
						}
					}

				} finally {
					stream.close();
					if (outStream != null) {
						outStream.close();
					}

				}
				success = true;
			} else {
				if (entity != null
						&& responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
					throw new ZLNetworkException(
							ZLNetworkException.SERVER_ERROR);
				} else {
					throw new ZLNetworkException(
							ZLNetworkException.ERROR_CONNECT_TO_HOST);
				}

			}
		} catch (ConnectTimeoutException e) {
			throw new ZLNetworkException(ZLNetworkException.NETWORK_TIME_OUT, e);
		} catch (ZLNetworkException e) {
			throw e;
		} catch (IOException e) {
			throw new ZLNetworkException(
					ZLNetworkException.ERROR_CONNECT_TO_HOST, e);
		} catch (Exception e) {
			// throw new ZLNetworkException(true, e.getMessage(), e);
		} finally {
			request.doAfter(success);
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
			if (entity != null) {
				try {
					entity.consumeContent();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	public Object perform(ZLNetworkRequest request,
			DataProtocol<?> mDataProtocol) throws ZLNetworkException {
		boolean success = false;
		DefaultHttpClient httpClient = null;
		HttpEntity entity = null;
		try {
			HttpContext httpContext = new BasicHttpContext();
			httpContext.setAttribute(ClientContext.COOKIE_STORE, myCookieStore);
			request.doBefore();
			HttpParams params = new BasicHttpParams();
			String netType = FunctionUtil.getNetType(MApplication.getInstance()
					.getBaseContext());
			if (!TextUtils.isEmpty(netType)
					&& netType.toLowerCase(Locale.CHINA).equals("wap")) {
				HttpHost proxy = new HttpHost("10.0.0.172", 80);
				params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			} else {
				params.setParameter(ConnRoutePNames.DEFAULT_PROXY, null);
			}
			HttpConnectionParams.setSoTimeout(params, 30000);// 设置等待数据超时时间15秒钟
			HttpConnectionParams.setConnectionTimeout(params, 30000);// 设置请求超时15秒钟
			HttpClientParams.setRedirecting(params, true);
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", new SSLSocketFactoryEx(), 443));
			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);
			httpClient = new DefaultHttpClient(ccm, params);
			httpClient.setHttpRequestRetryHandler(requestRetryHandler);
			HttpRequestBase httpRequest;
			// if (request.PostData != null) {
			// MyLog.d("request.PostData != null", request.URL);
			// httpRequest = new HttpPost(request.URL);
			// ((HttpPost) httpRequest).setEntity(new StringEntity(
			// request.PostData, HTTP.UTF_8));
			// /*
			// * httpConnection.setRequestProperty( "Content-Length",
			// * Integer.toString(request.PostData.getBytes().length) );
			// * httpConnection.setRequestProperty( "Content-Type",
			// * "application/x-www-form-urlencoded" );
			// */
			// } else if (!request.PostParameters.isEmpty()) {
			httpRequest = new HttpPost(request.URL);
			String signValue = MApplication.getInstance().getSignValue();
			// SimpleDateFormat sdf = new SimpleDateFormat(
			// "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
			// Date date = new Date(System.currentTimeMillis());
			// MyLog.d("date=", sdf.format(date));
			// request.PostParameters.put("timestamp", sdf.format(date));
			if (!TextUtils.isEmpty(signValue)) {
				signValue = buildRequestMysign(request.PostParameters,
						signValue);
				request.PostParameters.put("sign", signValue);
			}
			List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>(
					request.PostParameters.size());
			StringBuffer fianlUrl = new StringBuffer(request.URL);
			for (Map.Entry<String, String> entry : request.PostParameters
					.entrySet()) {
				list.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
				fianlUrl.append("&" + entry.getKey() + "=");
				fianlUrl.append(entry.getValue());
			}
			MyLog.d("postUrl=", fianlUrl.toString());

			((HttpPost) httpRequest).setEntity(new UrlEncodedFormEntity(list,
					HTTP.UTF_8));
			// } else {
			// MyLog.d("getUrl=", request.URL);
			// httpRequest = new HttpGet(request.URL);
			// }
			httpRequest.setHeader("User-Agent", ZLNetworkUtil.getUserAgent());
			if (!request.URL.endsWith("jcaptcha")) {
				httpRequest.setHeader("Accept-Encoding", "gzip");
			}
			httpRequest.setHeader("Accept-Language", Locale.getDefault()
					.getLanguage());
			httpRequest.getParams().setParameter("http.protocol.cooke-policy",
					CookiePolicy.BROWSER_COMPATIBILITY);
			// httpRequest.setHeader("Cookie", buf.toString());
			// httpRequest.setHeader("sessionid", sessionId);
			// MyLog.e("Cookie", "sessionid:" +
			// LeShiHuiApplication.getSessionId());
			// httpRequest.setHeader("sessionid",
			// LeShiHuiApplication.getSessionId());
			if (!request.heads.isEmpty()) {
				for (Map.Entry<String, String> entry : request.heads.entrySet()) {

					httpRequest.setHeader(entry.getKey(), entry.getValue());
				}
			}

			httpClient.setCredentialsProvider(new MyCredentialsProvider(
					httpRequest, request.isQuiet()));
			HttpResponse response = null;
			IOException lastException = null;
			for (int retryCounter = 0; retryCounter < 3 && entity == null; ++retryCounter) {
				try {
					response = httpClient.execute(httpRequest, httpContext);
					entity = response.getEntity();
					lastException = null;
					if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
						final AuthState state = (AuthState) httpContext
								.getAttribute(ClientContext.TARGET_AUTH_STATE);
						if (state != null) {
							final AuthScopeKey key = new AuthScopeKey(
									state.getAuthScope());
							if (myCredentialsCreator.removeCredentials(key)) {
								entity = null;
							}
						}
					}
				} catch (IOException e) {
					lastException = e;
				}
			}
			if (lastException != null) {
				throw lastException;
			}
			final int responseCode = response.getStatusLine().getStatusCode();
			MyLog.d("**********responseCode>>>>" + responseCode);
			InputStream stream = null;
			ByteArrayOutputStream outStream = null;
			if (entity != null && responseCode == HttpURLConnection.HTTP_OK) {
				BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(
						entity);

				stream = bufferedHttpEntity.getContent();
				List<Cookie> listCookies = myCookieStore.getCookies();
				if (listCookies.isEmpty()) {
					MyLog.e("Cookie", "Cookie is Empty");
				} else {
					MApplication.getInstance().setListCookies(listCookies);
					for (Cookie ck : listCookies) {
						MyLog.e("Cookie", ck.getName() + "--->" + ck.getValue());
						if ("JSESSIONID".equals(ck.getName())) {
							// sessionId = ck.getValue();
						}
					}
				}
			}
			if (stream != null) {
				try {
					outStream = new ByteArrayOutputStream();
					final Header encoding = entity.getContentEncoding();
					if (encoding != null
							&& "gzip".equalsIgnoreCase(encoding.getValue())) {
						stream = new GZIPInputStream(stream);
					}
					if (mDataProtocol != null) {
						String outputStream = null;
						byte[] data = new byte[1024];
						int count = -1;
						while ((count = stream.read(data, 0, 1024)) != -1)
							outStream.write(data, 0, count);
						data = null;
						outputStream = new String(outStream.toByteArray(),
								HTTP.UTF_8);
						MyLog.d("**********40response>>>>" + outputStream);
						// 替换
						JSONObject jsonObject = new JSONObject(outputStream);
						Head head = new Head(jsonObject);
						if (head != null
								&& (head.getCode().equals("40") || head
										.getCode().equals("49"))) {
							if (head.getCode().equals("40")) {
								MApplication.getInstance().setSignValue(
										head.getCodeMessage());
							}
							request.PostParameters.remove("sign");
							MyLog.d("********SIGN_ERROR************");
							throw new ZLNetworkException(
									ZLNetworkException.SIGN_ERROR);
						} else {
							MyLog.d("********************");
							return mDataProtocol.handle(outputStream);
						}

					} else {
						request.handleStream(stream,
								(int) entity.getContentLength());
					}

				} finally {
					stream.close();
					if (outStream != null) {
						outStream.close();
					}
				}
				success = true;
			} else {
				if (entity != null
						&& responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
					throw new ZLNetworkException(
							ZLNetworkException.SERVER_ERROR);
				} else {
					throw new ZLNetworkException(
							ZLNetworkException.ERROR_CONNECT_TO_HOST);
				}

			}
		} catch (ConnectTimeoutException e) {
			throw new ZLNetworkException(ZLNetworkException.NETWORK_TIME_OUT, e);
		} catch (ZLNetworkException e) {
			throw e;
		} catch (IOException e) {
			throw new ZLNetworkException(
					ZLNetworkException.ERROR_CONNECT_TO_HOST, e);
		} catch (Exception e) {
			// throw new ZLNetworkException(true, e.getMessage(), e);
		} finally {
			request.doAfter(success);
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
			if (entity != null) {
				try {
					entity.consumeContent();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	/**
	 * 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复
	 */
	private HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
		// 自定义的恢复策略
		public boolean retryRequest(IOException exception, int executionCount,
				HttpContext context) {
			// 设置恢复策略，在发生异常时候将自动重试1次
			MyLog.d("异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复");
			if (executionCount >= 1) {
				// Do not retry if over max retry count
				return false;
			}
			if (exception instanceof NoHttpResponseException) {
				// Retry if the server dropped connection on us
				return true;
			}
			if (exception instanceof SSLHandshakeException) {
				// Do not retry on SSL handshake exception
				return false;
			}
			HttpRequest request = (HttpRequest) context
					.getAttribute(ExecutionContext.HTTP_REQUEST);
			boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
			if (!idempotent) {
				// Retry if the request is considered idempotent
				return true;
			}
			return false;
		}
	};

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-4-27
	 */
	public Object perform(List<ZLNetworkRequest> requests)
			throws ZLNetworkException {
		if (requests.size() == 0) {
			return null;
		}
		if (requests.size() == 1) {
			return perform(requests.get(0));
		}
		HashSet<String> errors = new HashSet<String>();
		// TODO: implement concurrent execution !!!
		for (ZLNetworkRequest r : requests) {
			try {
				perform(r);
			} catch (ZLNetworkException e) {
				// e.printStackTrace();
				errors.add(e.getMessage());
			}
		}
		if (errors.size() > 0) {
			StringBuilder message = new StringBuilder();
			for (String e : errors) {
				if (message.length() != 0) {
					message.append(", ");
				}
				message.append(e);
			}
			throw new ZLNetworkException(true, message.toString());
		}
		return null;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-4-29
	 */
	public final void downloadToFile(String url, final File outFile)
			throws ZLNetworkException {
		downloadToFile(url, null, outFile, 8192);
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-4-29
	 */
	public final void downloadToFile(String url, String sslCertificate,
			final File outFile) throws ZLNetworkException {
		downloadToFile(url, sslCertificate, outFile, 8192);
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-4-27
	 */
	public final void downloadToFile(String url, String sslCertificate,
			final File outFile, final int bufferSize) throws ZLNetworkException {
		perform(new ZLNetworkRequest(url, sslCertificate, null) {
			public void handleStream(InputStream inputStream, int length)
					throws IOException, ZLNetworkException {
				OutputStream outStream = new FileOutputStream(outFile);
				try {
					final byte[] buffer = new byte[bufferSize];
					while (true) {
						final int size = inputStream.read(buffer);
						if (size <= 0) {
							break;
						}
						outStream.write(buffer, 0, size);
					}
				} finally {
					outStream.close();
				}
			}
		});
	}

	/**
	 * 生成签名结果
	 * 
	 * @param sPara
	 *            要签名的数组
	 * @return 签名结果字符串
	 */
	private String buildRequestMysign(Map<String, String> sPara, String key) {
		Map<String, String> fMap = paraFilter(sPara);
		// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String prestr = createLinkString(fMap);
		String mysign = "";
		mysign = sign(prestr, key, "utf-8");
		return mysign;
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	private Map<String, String> paraFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>();
		if (sArray == null || sArray.size() <= 0) {
			return result;
		}
		for (String key : sArray.keySet()) {

			String value = sArray.get(key);
			MyLog.d("key:" + key + "----value" + value);
			if (value == null || value.equals("")
					|| key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	private String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	/**
	 * 签名字符串
	 * 
	 * @param text
	 *            需要签名的字符串
	 * @param key
	 *            密钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果
	 */
	public String sign(String text, String key, String input_charset) {
		text = text + key;
		return md5Hex(getContentBytes(text, input_charset));
	}

	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名异常:指定的编码集【" + charset + "】不对");
		}
	}

	public String md5Hex(byte[] context) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(context);// update处理
			byte[] encryContext = md.digest();// 调用该方法完成计算

			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < encryContext.length; offset++) {// 做相应的转化（十六进制）
				i = encryContext[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
			// System.out.println("32result: " + buf.toString());// 32位的加密
			// System.out.println("16result: " + buf.toString().substring(8,
			// 24));// 16位的加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}