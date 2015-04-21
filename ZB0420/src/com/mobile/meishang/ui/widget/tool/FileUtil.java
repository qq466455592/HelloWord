/* 
 * @Title:  FileUtil.java 
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved 
 * @Description:  TODO<请描述此文件是做什么的> 
 * @author:  ZhangBin
 * @data:  2014-12-29 下午2:17:34 
 * @version:  V1.0 
 */
package com.mobile.meishang.ui.widget.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

public class FileUtil {

	private static int FILE_SIZE = 4 * 1024;

	public static boolean hasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	public static boolean createPath(String path) {
		File f = new File(path);
		if (!f.exists()) {
			Boolean o = f.mkdirs();
			Log.d("FileUtil", "create dir:" + path + ":" + o.toString());
			return o;
		}
		return true;
	}

	public static boolean exists(String file) {
		return new File(file).exists();
	}

	public static File saveFile(String file, InputStream inputStream) {
		File f = null;
		OutputStream outSm = null;

		try {
			f = new File(file);
			String path = f.getParent();
			if (!createPath(path)) {
				Log.d("FileUtil", "can't create dir:" + path);
				return null;
			}

			if (!f.exists()) {
				f.createNewFile();
			}

			outSm = new FileOutputStream(f);
			byte[] buffer = new byte[FILE_SIZE];
			while ((inputStream.read(buffer)) != -1) {
				outSm.write(buffer);
			}
			outSm.flush();
		} catch (IOException ex) {
			return null;

		} finally {
			try {
				if (outSm != null)
					outSm.close();
			} catch (IOException ex) {
			}
		}
		Log.d("[FileUtil]save file:", file + ":" + Boolean.toString(f.exists()));

		return f;
	}

	public static Drawable getImageDrawable(String file) {
		if (!exists(file))
			return null;
		try {
			InputStream inp = new FileInputStream(new File(file));
			return BitmapDrawable.createFromStream(inp, "img");
		} catch (Exception ex) {
		}
		return null;
	}
}