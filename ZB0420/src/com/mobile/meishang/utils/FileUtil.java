package com.mobile.meishang.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.mobile.meishang.logger.MyLog;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-4-27
 * @Version:
 */
public class FileUtil {
	public final static String SDCARD_MNT = "/mnt/sdcard";
	public final static String SDCARD = "/sdcard";
	private static Map<String, String> FORMAT_TO_CONTENTTYPE = new HashMap<String, String>();

	static {// 音频
		FORMAT_TO_CONTENTTYPE.put("mp3", "audio");
		FORMAT_TO_CONTENTTYPE.put("mid", "audio");
		FORMAT_TO_CONTENTTYPE.put("midi", "audio");
		FORMAT_TO_CONTENTTYPE.put("asf", "audio");
		FORMAT_TO_CONTENTTYPE.put("wm", "audio");
		FORMAT_TO_CONTENTTYPE.put("wma", "audio");
		FORMAT_TO_CONTENTTYPE.put("wmd", "audio");
		FORMAT_TO_CONTENTTYPE.put("amr", "audio");
		FORMAT_TO_CONTENTTYPE.put("wav", "audio");
		FORMAT_TO_CONTENTTYPE.put("3gpp", "audio");
		FORMAT_TO_CONTENTTYPE.put("mod", "audio");
		FORMAT_TO_CONTENTTYPE.put("mpc", "audio");

		// 视频
		FORMAT_TO_CONTENTTYPE.put("fla", "video");
		FORMAT_TO_CONTENTTYPE.put("flv", "video");
		FORMAT_TO_CONTENTTYPE.put("wav", "video");
		FORMAT_TO_CONTENTTYPE.put("wmv", "video");
		FORMAT_TO_CONTENTTYPE.put("avi", "video");
		FORMAT_TO_CONTENTTYPE.put("rm", "video");
		FORMAT_TO_CONTENTTYPE.put("rmvb", "video");
		FORMAT_TO_CONTENTTYPE.put("3gp", "video");
		FORMAT_TO_CONTENTTYPE.put("mp4", "video");
		FORMAT_TO_CONTENTTYPE.put("mov", "video");

		// flash
		FORMAT_TO_CONTENTTYPE.put("swf", "video");

		FORMAT_TO_CONTENTTYPE.put("null", "video");

		// 图片
		FORMAT_TO_CONTENTTYPE.put("jpg", "photo");
		FORMAT_TO_CONTENTTYPE.put("jpeg", "photo");
		FORMAT_TO_CONTENTTYPE.put("png", "photo");
		FORMAT_TO_CONTENTTYPE.put("bmp", "photo");
		FORMAT_TO_CONTENTTYPE.put("gif", "photo");
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public static boolean isSDCARDMounted() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public static void writeStringToFile(String content, String path) {
		File file = new File(path);
		BufferedWriter fw = null;
		try {
			fw = new BufferedWriter(new FileWriter(file, true));
			fw.write(content);
			fw.newLine();
		} catch (IOException e) {
			MyLog.e(e.getMessage());
		} finally {
			try {
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				MyLog.e(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public static String deserializeString(File file) {
		int len;
		char[] chr = new char[4096];
		final StringBuffer buffer = new StringBuffer();
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			while ((len = reader.read(chr)) > 0) {
				buffer.append(chr, 0, len);
			}
		} catch (IOException e) {
			MyLog.e(e.getMessage());
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				MyLog.e(e.getMessage());
			}
		}
		return buffer.toString();
	}

	/**
	 * �жϵ�ǰUrl�Ƿ��׼��content://��ʽ������ǣ��򷵻ؾ��·��
	 * 
	 * @param uri
	 * @return
	 */
	public static String getAbsolutePathFromNoStandardUri(Uri mUri) {
		String filePath = null;

		String mUriString = mUri.toString();
		mUriString = Uri.decode(mUriString);

		String pre1 = "file://" + SDCARD + File.separator;
		String pre2 = "file://" + SDCARD_MNT + File.separator;

		if (mUriString.startsWith(pre1)) {
			filePath = Environment.getExternalStorageDirectory().getPath()
					+ File.separator + mUriString.substring(pre1.length());
		} else if (mUriString.startsWith(pre2)) {
			filePath = Environment.getExternalStorageDirectory().getPath()
					+ File.separator + mUriString.substring(pre2.length());
		}
		return filePath;
	}

	/**
	 * ��ݸ��)չ���ȡ����
	 * 
	 * @param attFormat
	 * @return
	 */
	public static String getContentType(String attFormat) {
		String contentType = FORMAT_TO_CONTENTTYPE.get("null");

		if (attFormat != null) {
			contentType = (String) FORMAT_TO_CONTENTTYPE.get(attFormat
					.toLowerCase());
		}
		return contentType;
	}

	/**
	 * ��sdcard��ͼƬת��Ϊbitmap����
	 * 
	 * @param imgFilePath
	 * @return
	 */
	public static Bitmap getBitmapFromSd(String imgFilePath) {
		try {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(imgFilePath, opts);
			opts.inSampleSize = computeSampleSize(opts, -1, 128 * 128);
			opts.inJustDecodeBounds = false;
			final Bitmap bmp = BitmapFactory.decodeFile(imgFilePath, opts);
			return bmp;

		} catch (Exception e) {
			MyLog.e("Exception", e.getMessage());
		}
		return null;
	}

	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

}
