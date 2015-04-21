/* 
 * @Title:  NetWork.java 
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved 
 * @Description:  TODO<请描述此文件是做什么的> 
 * @author:  ZhangBin
 * @data:  2014-12-29 下午2:20:07 
 * @version:  V1.0 
 */
package com.mobile.meishang.ui.widget.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class NetWork {
	 
    public static String getHttpData(String baseUrl){
        return getHttpData(baseUrl, "GET", "", null);
    }
 
    public static String postHttpData(String baseUrl, String reqData){
        return getHttpData(baseUrl, "POST", reqData, null);
    }
 
    public static String postHttpData(String baseUrl, String reqData, HashMap<String, String> propertys){
        return getHttpData(baseUrl, "POST", reqData, propertys);
    }
 
    /**
     * 获取赛事信息
     * @return
     */
    public static String getHttpData(String baseUrl, String method, String reqData, HashMap<String, String> propertys){
        String data = "", str;
        PrintWriter outWrite = null;
        InputStream inpStream = null;
        BufferedReader reader = null;
 
        HttpURLConnection urlConn = null;
        try{
            URL url = new URL(baseUrl);
            urlConn = (HttpURLConnection)url.openConnection();
            //启用gzip压缩
            urlConn.addRequestProperty("Accept-Encoding", "gzip, deflate");
            urlConn.setRequestMethod(method);
            urlConn.setDoOutput(true);
			urlConn.setConnectTimeout(30000);
            if(propertys != null && !propertys.isEmpty()){
                Iterator<Map.Entry<String, String>> props = propertys.entrySet().iterator();
                Map.Entry<String, String> entry;
                while (props.hasNext()){
                    entry = props.next();
                    urlConn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
 
            outWrite = new PrintWriter(urlConn.getOutputStream());
            outWrite.print(reqData);
            outWrite.flush();
 
            urlConn.connect();
 
            //获取数据流
            inpStream = urlConn.getInputStream();
            String encode = urlConn.getHeaderField("Content-Encoding");
 
            //如果通过gzip
            if(encode !=null && encode.indexOf("gzip") != -1){
                Log.d("", "get data :" + encode);
                inpStream = new GZIPInputStream(inpStream);
            }else if(encode != null && encode.indexOf("deflate") != -1){
                inpStream = new InflaterInputStream(inpStream);
            }
 
            reader = new BufferedReader(new InputStreamReader(inpStream));
 
            while((str = reader.readLine()) != null){
                data += str;
            }
        }catch (MalformedURLException ex){
        }catch (IOException ex){
        }finally{
            if(reader !=null && urlConn!=null){
                try {
                    outWrite.close();
                    inpStream.close();
                    reader.close();
                    urlConn.disconnect();
                } catch (IOException ex) {
                }
            }
        }
 
        Log.d("", "[Http data]["+baseUrl+"]:" + data);
        return data;
    }
 
    /**
     * 获取Image信息
     * @return
     */
    public static Bitmap getBitmapData(String imgUrl){
        Bitmap bmp = null;
        Log.d("", "get imgage:"+imgUrl);
        InputStream inpStream = null;
        try{
            HttpGet http = new HttpGet(imgUrl);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = (HttpResponse)client.execute(http);
            HttpEntity httpEntity = response.getEntity();
            BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(httpEntity);
 
            //获取数据流
            inpStream = bufferedHttpEntity.getContent();
            bmp = BitmapFactory.decodeStream(inpStream);
 
        }catch (Exception ex){
        }finally{
            if(inpStream !=null){
                try {
                    inpStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
 
        return bmp;
    }
 
    /**
     * 获取url的InputStream
     * @param urlStr
     * @return
     */
    public static InputStream getInputStream(String urlStr){
        Log.d("", "get http input:"+urlStr);
        InputStream inpStream = null;
        try{
            HttpGet http = new HttpGet(urlStr);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = (HttpResponse)client.execute(http);
            HttpEntity httpEntity = response.getEntity();
            BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(httpEntity);
 
            //获取数据流
            inpStream = bufferedHttpEntity.getContent();
        }catch (Exception ex){
        }finally{
            if(inpStream !=null){
                try {
                    inpStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return inpStream;
    }
}